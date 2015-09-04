package by.epam.training.task06.connection;

import by.epam.training.task06.exception.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * <p>
 *      Provides connection pool for application
 * </p>
 */
public class ConnectionPool {
    private volatile static ConnectionPool instance;
    private BlockingQueue<Connection> freeConnections;
    private Map<Long, Connection> connectionOwners;

    private final static int CONNECTIONS_COUNT = 10;

    private final String CONFIG = "resources/jdbc";
    private final String DRIVER = "driver";
    private final String URL = "driver_url";
    private final String USER = "driver_user";
    private final String PASS = "driver_password";

    public static ConnectionPool getInstance() throws DaoException {
        if (null == instance) {
            synchronized (ConnectionPool.class) {
                if (null == instance) {
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }

    private ConnectionPool() throws DaoException {
        freeConnections = new LinkedBlockingQueue<>(CONNECTIONS_COUNT);
        connectionOwners = new HashMap<>();
        ResourceBundle config = ResourceBundle.getBundle(CONFIG);
        try {
            Class.forName(config.getString(DRIVER));
            for (int i = 0; i < CONNECTIONS_COUNT; i++) {
                Connection connection = DriverManager.getConnection(
                        config.getString(URL), config.getString(USER), config.getString(PASS));
                freeConnections.add(connection);
            }
        } catch (ClassNotFoundException e) {
            throw new DaoException("Can't create pool, class not found", e);
        } catch (SQLException e) {
            throw new DaoException("Can't create pool, sql error", e);
        }
    }

    public Connection getConnection() throws DaoException {
        Connection connection;
        Long currentThreadId = Thread.currentThread().getId();

        if (connectionOwners.containsKey(currentThreadId)) {
            return connectionOwners.get(currentThreadId);
        } else {
            try {
                connection = freeConnections.take();
                connectionOwners.put(currentThreadId, connection);
            } catch (InterruptedException e) {
                throw new DaoException("Can't take a connection!", e);
            }
            return connection;
        }
    }

    public void returnConnection() throws DaoException {
        Long threadOwnerId = Thread.currentThread().getId();
        if (connectionOwners.containsKey(threadOwnerId)) {
            try {
                freeConnections.put(connectionOwners.get(threadOwnerId));
                connectionOwners.remove(threadOwnerId);
            } catch (InterruptedException e) {
                throw new DaoException("Can't put pool connection back", e);
            }
        } else {
            throw new DaoException("Try return wrong connection!");
        }
    }

    public void destroy() throws SQLException {
        for (Connection connection : freeConnections) {
            connection.close();
        }
        connectionOwners.clear();
    }
}