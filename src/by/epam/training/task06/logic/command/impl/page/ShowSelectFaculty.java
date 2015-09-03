package by.epam.training.task06.logic.command.impl.page;

import by.epam.training.task06.dao.ConnectionPool;
import by.epam.training.task06.dao.DaoException;
import by.epam.training.task06.dao.impl.FacultyDaoMySQL;
import by.epam.training.task06.entity.Faculty;
import by.epam.training.task06.logic.LogicException;
import by.epam.training.task06.logic.CommandOptionToActionMapping;
import by.epam.training.task06.logic.UrlToCommandMapping;
import by.epam.training.task06.logic.command.Command;
import by.epam.training.task06.page.UserPage;
import by.epam.training.task06.parameter.FacultyParameter;
import by.epam.training.task06.parameter.PageParameter;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *     Allows show select faculty form
 * </p>
 */
public class ShowSelectFaculty implements Command {
    @Override
    public String execute(HttpServletRequest request) throws LogicException {
        List<Faculty> faculties;
        try {
            faculties = FacultyDaoMySQL.getInstance().loadAll();
        } catch (DaoException e) {
            throw new LogicException("Can't load faculties list!");
        } finally {
            try {
                ConnectionPool.getInstance().returnConnection();
            } catch (DaoException e) {
                throw new LogicException("Can't close connection! Reason - ", e);
            }
        }
        String commandOption = request.getParameter(PageParameter.COMMAND_OPTION);
        String action = CommandOptionToActionMapping.getInstance().getAction(commandOption);

        request.setAttribute(FacultyParameter.FACULTIES, faculties);
        request.setAttribute(PageParameter.COMMAND_OPTION, commandOption);
        request.setAttribute(PageParameter.SELECT_FACULTY_ACTION, action);
        request.setAttribute(PageParameter.SWITCH_LANG_ACTION, UrlToCommandMapping.SELECT_FACULTY);
        return UserPage.SELECT_FACULTY;
    }
}
