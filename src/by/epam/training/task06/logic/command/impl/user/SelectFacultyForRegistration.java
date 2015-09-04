package by.epam.training.task06.logic.command.impl.user;

import by.epam.training.task06.dao.query.QueryOption;
import by.epam.training.task06.entity.Discipline;
import by.epam.training.task06.entity.User;
import by.epam.training.task06.exception.LogicException;
import by.epam.training.task06.logic.help.LogicHelp;
import by.epam.training.task06.logic.help.UrlToCommandMapping;
import by.epam.training.task06.logic.command.Command;
import by.epam.training.task06.page.UserPage;
import by.epam.training.task06.parameter.FacultyParameter;
import by.epam.training.task06.parameter.PageParameter;
import by.epam.training.task06.parameter.UserParameter;
import by.epam.training.task06.constants.query_template.FacultyDaoQuery;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *     Allows show registration form after selecting a faculty at which it should be done
 * </p>
 */
public class SelectFacultyForRegistration implements Command {
    @Override
    public String execute(HttpServletRequest request) throws LogicException {
        String facultyName = request.getParameter(FacultyParameter.FACULTY_NAME);
        String commandOption = request.getParameter(PageParameter.COMMAND_OPTION);

        HttpSession session = request.getSession(false);
        if (null == session) {
            throw new LogicException("Can't get session!");
        }

        User user = (User) session.getAttribute(UserParameter.USER);

        QueryOption facultyReadOption = new QueryOption();
        facultyReadOption.setQueryTemplate(FacultyDaoQuery.READ_FACULTY_BY_NAME);
        facultyReadOption.addParameter(facultyName);

        List<Discipline> facultyDisciplines = LogicHelp.getDisciplines(facultyName);

        session.setAttribute(UserParameter.USER, user);
        request.setAttribute(FacultyParameter.FACULTY_NAME, facultyName);
        request.setAttribute(UserParameter.FACULTY_DISCIPLINES, facultyDisciplines);
        request.setAttribute(PageParameter.COMMAND_OPTION, commandOption);
        request.setAttribute(PageParameter.SWITCH_LANG_ACTION, UrlToCommandMapping.REGISTER_AT_FACULTY);
        return UserPage.REGISTER_AT_FACULTY;
    }
}
