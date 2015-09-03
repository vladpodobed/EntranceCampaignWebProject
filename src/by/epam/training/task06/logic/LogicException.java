package by.epam.training.task06.logic;

import by.epam.training.task06.exception.TaskException;


public class LogicException extends TaskException {
    public LogicException() {
        super();
    }

    public LogicException(String message) {
        super(message);
    }

    public LogicException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogicException(Throwable cause) {
        super(cause);
    }
}
