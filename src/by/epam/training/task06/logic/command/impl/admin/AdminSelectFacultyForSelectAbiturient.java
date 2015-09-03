package by.epam.training.task06.logic.command.impl.admin;

import by.epam.training.task06.dao.DaoException;
import by.epam.training.task06.dao.QueryOption;
import by.epam.training.task06.dao.impl.UserDaoMySQL;
import by.epam.training.task06.entity.Discipline;
import by.epam.training.task06.entity.User;
import by.epam.training.task06.logic.LogicException;
import by.epam.training.task06.logic.LogicHelp;
import by.epam.training.task06.logic.UrlToCommandMapping;
import by.epam.training.task06.logic.command.Command;
import by.epam.training.task06.page.AdminPage;
import by.epam.training.task06.parameter.FacultyParameter;
import by.epam.training.task06.parameter.PageParameter;
import by.epam.training.task06.parameter.UserParameter;
import by.epam.training.task06.util.daoutil.UserDaoQuery;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *     Allows select abiturints after selecting a faculty at which it should be done
 * </p>
 */
public class AdminSelectFacultyForSelectAbiturient implements Command {
    @Override
    public String execute(HttpServletRequest request) throws LogicException {
        String facultyName = request.getParameter(FacultyParameter.FACULTY_NAME);
        String commandOption = request.getParameter(PageParameter.COMMAND_OPTION);


        QueryOption statusAndFacultyNameUserOption = new QueryOption();
        statusAndFacultyNameUserOption.setQueryTemplate(UserDaoQuery.READ_USER_BY_STATUS_AND_FACULTY_NAME);
        statusAndFacultyNameUserOption.addParameter(UserParameter.DOCUMENTS_PROCCESSING_STATUS);
        statusAndFacultyNameUserOption.addParameter(facultyName);

        List<User> candidates;
        try {
            candidates = UserDaoMySQL.getInstance().select(statusAndFacultyNameUserOption);
        } catch (DaoException e) {
            throw new LogicException("Users extraction error! Reason - ", e);
        }

        List<Discipline> facultyDisciplines = LogicHelp.getDisciplines(facultyName);

        request.setAttribute(FacultyParameter.FACULTY_NAME, facultyName);
        request.setAttribute(UserParameter.CANDIDATES, candidates);
        request.setAttribute(UserParameter.FACULTY_DISCIPLINES, facultyDisciplines);
        request.setAttribute(PageParameter.COMMAND_OPTION, commandOption);
        request.setAttribute(PageParameter.SWITCH_LANG_ACTION, UrlToCommandMapping.SELECT_ABITURIENT);
        return AdminPage.SELECT_ABITURIENT;
    }
}
