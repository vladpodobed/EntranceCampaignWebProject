package by.epam.training.task06.logic.command;

import by.epam.training.task06.exception.LogicException;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *      Basic interface for commands
 * </p>
 */
public interface Command {
    /**
     * <p>
     *     Gets request parameters or/and session from HttpServletRequest, do some logic (load or save data from/to DAO)
     *     and returns Jsp page location
     * </p>
     *
     * @param request
     * @return Jsp page location
     * @throws LogicException
     */
    public String execute(HttpServletRequest request) throws LogicException;
}
