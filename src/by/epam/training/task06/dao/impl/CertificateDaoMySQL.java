package by.epam.training.task06.dao.impl;

import by.epam.training.task06.dao.ConnectionPool;
import by.epam.training.task06.dao.DaoException;
import by.epam.training.task06.dao.DaoMySQL;
import by.epam.training.task06.dao.QueryOption;
import by.epam.training.task06.entity.Certificate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *      Provides ability to work with certificates data
 * </p>
 */
public class CertificateDaoMySQL implements DaoMySQL<Certificate> {
    private static CertificateDaoMySQL instance = new CertificateDaoMySQL();

    private final String CREATE_CERTIFICATE = "INSERT INTO university.certificate (user_id, discipline_id, " +
            "result) VALUES (?, ?, ?);";
    private final String READ_CERTIFICATE = "SELECT * FROM university.certificate WHERE certificate_id=?;";
    private final String UPDATE_CERTIFICATE = "UPDATE university.certificate SET user_id=?, " +
            "discipline_id=?, result=? WHERE certificate_id=?;";
    private final String DELETE_CERTIFICATE = "DELETE FROM  university.certificate WHERE certificate_id=?";
    private final String READ_ALL_CERTIFICATES = "SELECT * FROM university.certificate;";
    private final String READ_LAST_ID = "SELECT MAX(certificate_id) FROM university.certificate;";

    private CertificateDaoMySQL() {

    }

    public static CertificateDaoMySQL getInstance() {
        return instance;
    }

    @Override
    public int create(Certificate certificate) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        int id = 0;
        try {
            statement = connection.prepareStatement(CREATE_CERTIFICATE);
            statement.setInt(1, certificate.getUserId());
            statement.setInt(2, certificate.getDisciplineId());
            statement.setInt(3, certificate.getResult());
            statement.executeUpdate();
            statement.close();
            statement = connection.prepareStatement(READ_LAST_ID);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                id = result.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException("Error when creating a certificate!", e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Can't close statement when creating a certificate!", e);
            }
        }
        return id;
    }

    @Override
    public Certificate read(int id) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        Certificate certificate = null;
        try {
            statement = connection.prepareStatement(READ_CERTIFICATE);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int userId = result.getInt(2);
                int disciplineId = result.getInt(3);
                int resultMark = result.getInt(4);
                certificate = new Certificate(id, userId, disciplineId, resultMark);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't execute sql when reading a certificate!", e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Can't close statement when reading a certificate!", e);
            }
        }
        return certificate;
    }

    @Override
    public void update(Certificate certificate) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_CERTIFICATE);
            statement.setInt(1, certificate.getUserId());
            statement.setInt(2, certificate.getDisciplineId());
            statement.setInt(3, certificate.getResult());
            statement.setInt(4, certificate.getId());
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
            statement = connection.prepareStatement(DELETE_CERTIFICATE);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Can't execute sql when deleting a certificate!", e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Can't close statement when deleting a certificate!", e);
            }
        }
    }

    @Override
    public List loadAll() throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        List<Certificate> certificates = new LinkedList<>();
        try {
            statement = connection.prepareStatement(READ_ALL_CERTIFICATES);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int id = result.getInt(1);
                int userId = result.getInt(2);
                int disciplineId = result.getInt(3);
                int resultMark = result.getInt(4);
                Certificate certificate = new Certificate(id, userId, disciplineId, resultMark);
                certificates.add(certificate);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't execute sql when reading certificates!", e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Can't close statement when reading certificates!", e);
            }
        }
        return certificates;
    }

    @Override
    public List select(QueryOption option) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        List<Certificate> certificates = new LinkedList<>();
        try {
            statement = connection.prepareStatement(option.getQueryTemplate());
            int index = 1;
            for (Object o : option.getParameters()) {
                statement.setObject(index++, o);
            }
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int id = result.getInt(1);
                int userId = result.getInt(2);
                int disciplineId = result.getInt(3);
                int resultMark = result.getInt(4);
                Certificate certificate = new Certificate(id, userId, disciplineId, resultMark);
                certificates.add(certificate);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't execute sql when reading certificates!", e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Can't close statement when reading certificates!", e);
            }
        }
        return certificates;
    }
}
