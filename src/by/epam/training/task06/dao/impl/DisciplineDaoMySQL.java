package by.epam.training.task06.dao.impl;

import by.epam.training.task06.dao.ConnectionPool;
import by.epam.training.task06.dao.DaoException;
import by.epam.training.task06.dao.DaoMySQL;
import by.epam.training.task06.dao.QueryOption;
import by.epam.training.task06.entity.Discipline;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *     Provides ability to work with disciplines data
 * </p>
 */
public class DisciplineDaoMySQL implements DaoMySQL<Discipline> {
    private static DisciplineDaoMySQL instance = new DisciplineDaoMySQL();

    private final String CREATE_DISCIPLINE = "INSERT INTO university.discipline (discipline_id, name) VALUES (?, ?);";
    private final String READ_DISCIPLINE = "SELECT * FROM university.discipline WHERE discipline_id=?;";
    private final String UPDATE_DISCIPLINE = "UPDATE university.discipline SET discipline_id, name=? WHERE discipline_id=?;";
    private final String DELETE_DISCIPLINE = "DELETE FROM  university.discipline WHERE discipline_id=?";
    private final String LOAD_ALL_DISCIPLINES = "SELECT * FROM university.discipline;";
    private final String READ_LAST_ID = "SELECT MAX(discipline_id) FROM university.discipline;";

    private DisciplineDaoMySQL() {

    }

    public static DisciplineDaoMySQL getInstance() {
        return instance;
    }

    @Override
    public int create(Discipline discipline) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        int id = 0;
        try {
            statement = connection.prepareStatement(CREATE_DISCIPLINE);
            statement.setInt(1, discipline.getId());
            statement.setString(2, discipline.getName());
            statement.executeUpdate();
            statement.close();
            statement = connection.prepareStatement(READ_LAST_ID);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                id = result.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException("Error when creating a discipline!", e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Can't close statement when creating a discipline!", e);
            }
        }
        return id;
    }

    @Override
    public Discipline read(int id) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        Discipline discipline = null;
        try {
            statement = connection.prepareStatement(READ_DISCIPLINE);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String name = result.getString(2);
                discipline = new Discipline(id, name);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't execute sql when reading a discipline!", e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Can't close statement when reading a discipline!", e);
            }
        }
        return discipline;
    }

    @Override
    public void update(Discipline discipline) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_DISCIPLINE);
            statement.setInt(1, discipline.getId());
            statement.setString(2, discipline.getName());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new DaoException("Can't execute sql when updating a discipline!", e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Can't close statement when updating a discipline!", e);
            }
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_DISCIPLINE);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Can't execute sql when deleting a discipline!", e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Can't close statement when deleting a discipline!", e);
            }
        }
    }

    @Override
    public List<Discipline> loadAll() throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        List<Discipline> disciplines = new LinkedList<>();
        try {
            statement = connection.prepareStatement(LOAD_ALL_DISCIPLINES);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int id = result.getInt(1);
                String name = result.getString(2);
                Discipline discipline = new Discipline(id, name);
                disciplines.add(discipline);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't execute sql when loading disciplines!", e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Can't close statement when loading disciplines!", e);
            }
        }
        return disciplines;
    }

    @Override
    public List<Discipline> select(QueryOption option) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        List<Discipline> disciplines = new LinkedList<>();
        try {
            statement = connection.prepareStatement(option.getQueryTemplate());
            int index = 1;
            for (Object o : option.getParameters()) {
                statement.setObject(index++, o);
            }
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int id = result.getInt(1);
                String name = result.getString(2);
                Discipline discipline = new Discipline(id, name);
                disciplines.add(discipline);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't execute sql when reading disciplines!", e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Can't close statement when reading disciplines!", e);
            }
        }
        return disciplines;
    }
}
