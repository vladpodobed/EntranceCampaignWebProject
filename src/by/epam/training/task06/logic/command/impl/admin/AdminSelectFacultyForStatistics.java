package by.epam.training.task06.logic.command.impl.admin;

import by.epam.training.task06.exception.DaoException;
import by.epam.training.task06.dao.query.QueryOption;
import by.epam.training.task06.dao.impl.UserDAO;
import by.epam.training.task06.entity.Discipline;
import by.epam.training.task06.entity.User;
import by.epam.training.task06.exception.LogicException;
import by.epam.training.task06.logic.help.LogicHelp;
import by.epam.training.task06.logic.help.UrlToCommandMapping;
import by.epam.training.task06.logic.command.Command;
import by.epam.training.task06.page.AdminPage;
import by.epam.training.task06.parameter.FacultyParameter;
import by.epam.training.task06.parameter.PageParameter;
import by.epam.training.task06.parameter.UserParameter;
import by.epam.training.task06.constants.query_template.UserDaoQuery;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *      Allows show faculty statistics after selecting a faculty at which it should be done
 * </p>
 */
public class AdminSelectFacultyForStatistics implements Command {
    @Override
    public String execute(HttpServletRequest request) throws LogicException {
        String facultyName = request.getParameter(FacultyParameter.FACULTY_NAME);
        String commandOption = request.getParameter(PageParameter.COMMAND_OPTION);

        QueryOption facultyReadOption = new QueryOption();
        facultyReadOption.setQueryTemplate(UserDaoQuery.READ_USER_BY_FACULTY_NAME);
        facultyReadOption.addParameter(facultyName);

        List<User> candidates;
        try {
            candidates = UserDAO.getInstance().select(facultyReadOption);
        } catch (DaoException e) {
            throw new LogicException("Faculties extraction error! Reason - ", e);
        }

        List<Discipline> facultyDisciplines = LogicHelp.getDisciplines(facultyName);

        request.setAttribute(FacultyParameter.FACULTY_NAME, facultyName);
        request.setAttribute(UserParameter.CANDIDATES, candidates);
        request.setAttribute(UserParameter.FACULTY_DISCIPLINES, facultyDisciplines);
        request.setAttribute(PageParameter.COMMAND_OPTION, commandOption);
        request.setAttribute(PageParameter.SWITCH_LANG_ACTION, UrlToCommandMapping.FACULTY_STATISTICS);
        return AdminPage.FACULTY_STATISTICS;
    }
}
