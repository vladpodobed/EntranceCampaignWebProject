package by.epam.training.task06.dao.impl;

import by.epam.training.task06.connection.ConnectionPool;
import by.epam.training.task06.exception.DaoException;
import by.epam.training.task06.dao.DAO;
import by.epam.training.task06.dao.query.QueryOption;
import by.epam.training.task06.entity.Certificate;
import by.epam.training.task06.entity.User;
import by.epam.training.task06.constants.query_template.CertificateDaoQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *      Provides ability to work with user data
 * </p>
 */
public class UserDAO implements DAO<User> {
    private static UserDAO instance = new UserDAO();

    private final String CREATE_USER = "INSERT INTO university.site_user (status, email, password, full_name) " +
            "VALUES (?, ?, ?, ?);";
    private final String READ_USER = "SELECT * FROM university.site_user WHERE user_id=?;";
    private final String UPDATE_USER = "UPDATE university.site_user SET status=?, email=?, password=?, " +
            "full_name=?, certificate_score=?, faculty_id=? " +
            "WHERE user_id=?;";
    private final String READ_ALL_USERS = "SELECT * FROM university.site_user;";
    private final String READ_LAST_ID = "SELECT MAX(user_id) FROM university.site_user;";
    private final String ALTER_TABLE_INDEX_INCREMENT = "ALTER TABLE university.site_user AUTO_INCREMENT = 1;";

    private UserDAO() {

    }

    public static UserDAO getInstance() {
        return instance;
    }

    @Override
    public int create(User user) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        int id = 0;
        try {
            statement = connection.prepareStatement(CREATE_USER);
            statement.setString(1, user.getStatus());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getName());
            statement.executeUpdate();
            statement.close();
            statement = connection.prepareStatement(READ_LAST_ID);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                id = result.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException("Error when add a user!", e);
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
    public User read(int id) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        User user = null;
        try {
            statement = connection.prepareStatement(READ_USER);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String status = result.getString(2);
                String email = result.getString(3);
                String password = result.getString(4);
                String fullName = result.getString(5);
                double schoolCert = result.getDouble(6);
                int facultyId = result.getInt(7);

                QueryOption certificatesByUserOption = new QueryOption();
                certificatesByUserOption.setQueryTemplate(CertificateDaoQuery.READ_CERTIFICATE_BY_USER_ID);
                certificatesByUserOption.addParameter(id);
                List<Certificate> certificates = CertificateDAO.getInstance().select(certificatesByUserOption);
                user = new User(id, status, email, password, fullName, schoolCert, certificates, facultyId);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't execute sql when reading a user!", e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Can't close statement when reading a user!", e);
            }
        }
        return user;
    }

    @Override
    public void update(User user) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_USER);
            statement.setString(1, user.getStatus());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getName());
            statement.setDouble(5, user.getSchoolCertificateScore());
            statement.setInt(6, user.getFacultyId());
            statement.setInt(7, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error when updating a user!", e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Can't close statement when updating a user!", e);
            }
        }

    }

    @Override
    public void delete(int id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> loadAll() throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        List<User> users = new LinkedList<>();
        try {
            statement = connection.prepareStatement(READ_ALL_USERS);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int id = result.getInt(1);
                String status = result.getString(2);
                String email = result.getString(3);
                String password = result.getString(4);
                String fullName = result.getString(5);
                double schoolCert = result.getDouble(6);
                int facultyId = result.getInt(7);

                QueryOption certificatesByUserOption = new QueryOption();
                certificatesByUserOption.setQueryTemplate(CertificateDaoQuery.READ_CERTIFICATE_BY_USER_ID);
                certificatesByUserOption.addParameter(id);
                List<Certificate> certificates = CertificateDAO.getInstance().select(certificatesByUserOption);
                User user = new User(id, status, email, password, fullName, schoolCert, certificates, facultyId);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't execute sql when reading users!", e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Can't close statement when reading users!", e);
            }
        }
        return users;
    }

    @Override
    public List<User> select(QueryOption option) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        List<User> users = new LinkedList<>();
        try {
            statement = connection.prepareStatement(option.getQueryTemplate());
            int index = 1;
            for (Object o : option.getParameters()) {
                statement.setObject(index++, o);
            }
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int id = result.getInt(1);
                String status = result.getString(2);
                String email = result.getString(3);
                String password = result.getString(4);
                String fullName = result.getString(5);
                double schoolCert = result.getDouble(6);
                int facultyId = result.getInt(7);

                QueryOption certificatesByUserOption = new QueryOption();
                certificatesByUserOption.setQueryTemplate(CertificateDaoQuery.READ_CERTIFICATE_BY_USER_ID);
                certificatesByUserOption.addParameter(id);
                List<Certificate> certificates = CertificateDAO.getInstance().select(certificatesByUserOption);
                User user = new User(id, status, email, password, fullName, schoolCert, certificates, facultyId);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't execute sql when reading users!", e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Can't close statement when reading users!", e);
            }
        }
        return users;
    }
}
