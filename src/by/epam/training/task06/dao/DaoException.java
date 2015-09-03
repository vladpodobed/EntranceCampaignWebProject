package by.epam.training.task06.dao;

import by.epam.training.task06.exception.TaskException;


public class DaoException extends TaskException {
    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
