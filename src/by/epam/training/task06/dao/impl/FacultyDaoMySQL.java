package by.epam.training.task06.dao.impl;

import by.epam.training.task06.dao.ConnectionPool;
import by.epam.training.task06.dao.DaoException;
import by.epam.training.task06.dao.DaoMySQL;
import by.epam.training.task06.dao.QueryOption;
import by.epam.training.task06.entity.Faculty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *      Provides ability to work with faculty data
 * </p>
 */
public class FacultyDaoMySQL implements DaoMySQL<Faculty> {
    private static FacultyDaoMySQL instance = new FacultyDaoMySQL();

    private final String CREATE_FACULTY = "INSERT INTO university.faculty (name, enrollment) VALUES (?, ?);";
    private final String READ_FACULTY = "SELECT * FROM university.faculty WHERE faculty_id=?;";
    private final String UPDATE_FACULTY = "UPDATE university.faculty SET name=?, enrollment=? WHERE faculty_id=?;";
    private final String DELETE_FACULTY = "DELETE FROM  university.faculty WHERE faculty_id=?";
    private final String LOAD_ALL_FACULTIES = "SELECT * FROM university.faculty;";
    private final String READ_LAST_ID = "SELECT MAX(faculty_id) FROM university.faculty;";

    private FacultyDaoMySQL() {

    }

    public static FacultyDaoMySQL getInstance() {
        return instance;
    }

    @Override
    public int create(Faculty faculty) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        int id = 0;
        try {
            statement = connection.prepareStatement(CREATE_FACULTY);
            statement.setString(1, faculty.getName());
            statement.setInt(2, faculty.getEnrollment());
            statement.executeUpdate();
            statement.close();
            statement = connection.prepareStatement(READ_LAST_ID);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                id = result.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException("Error when creating a faculty!", e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Can't close statement when creating a faculty!", e);
            }
        }
        return id;
    }

    @Override
    public Faculty read(int id) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        Faculty faculty = null;
        try {
            statement = connection.prepareStatement(READ_FACULTY);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String name = result.getString(2);
                int enrollment = result.getInt(3);
                faculty = new Faculty(id, name, enrollment);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't execute sql when reading a faculty!", e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Can't close statement when reading a faculty!", e);
            }
        }
        return faculty;
    }

    @Override
    public void update(Faculty faculty) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_FACULTY);
            statement.setString(1, faculty.getName());
            statement.setInt(2, faculty.getEnrollment());
            statement.setInt(3, faculty.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new DaoException("Can't execute sql when updating a faculty!", e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Can't close statement when updating a faculty!", e);
            }
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_FACULTY);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Can't execute sql when deleting a faculty!", e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Can't close statement when deleting a faculty!", e);
            }
        }
    }

    @Override
    public List loadAll() throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        List<Faculty> faculties = new LinkedList<>();
        try {
            statement = connection.prepareStatement(LOAD_ALL_FACULTIES);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int id = result.getInt(1);
                String name = result.getString(2);
                int enrollment = result.getInt(3);
                Faculty faculty = new Faculty(id, name, enrollment);
                faculties.add(faculty);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't execute sql when loading faculties!", e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Can't close statement when loading faculties!", e);
            }
        }
        return faculties;
    }

    @Override
    public List select(QueryOption option) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        List<Faculty> faculties = new LinkedList<>();
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
                int enrollment = result.getInt(3);
                Faculty faculty = new Faculty(id, name, enrollment);
                faculties.add(faculty);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't execute sql when reading faculties!", e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Can't close statement when reading faculties!", e);
            }
        }
        return faculties;
    }
}