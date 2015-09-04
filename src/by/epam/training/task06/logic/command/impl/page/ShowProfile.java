package by.epam.training.task06.logic.command.impl.page;

import by.epam.training.task06.connection.ConnectionPool;
import by.epam.training.task06.exception.DaoException;
import by.epam.training.task06.dao.query.QueryOption;
import by.epam.training.task06.dao.impl.DisciplineDAO;
import by.epam.training.task06.dao.impl.FacultyDAO;
import by.epam.training.task06.entity.Discipline;
import by.epam.training.task06.entity.Faculty;
import by.epam.training.task06.entity.User;
import by.epam.training.task06.exception.LogicException;
import by.epam.training.task06.logic.help.UrlToCommandMapping;
import by.epam.training.task06.logic.command.Command;
import by.epam.training.task06.page.UserPage;
import by.epam.training.task06.parameter.PageParameter;
import by.epam.training.task06.parameter.UserParameter;
import by.epam.training.task06.constants.query_template.DisciplineDaoQuery;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *     Allows show user profile
 * </p>
 */
public class ShowProfile implements Command {
    @Override
    public String execute(HttpServletRequest request) throws LogicException {
        HttpSession session = request.getSession(false);
        if (null == session){
            throw new LogicException("Can't get session!");
        }

        User user = (User) session.getAttribute(UserParameter.USER);
        if(UserParameter.ADMIN_STATUS.equals(user.getStatus()) || UserParameter.VISITOR_STATUS.equals(user.getStatus())) {
            request.setAttribute(PageParameter.SWITCH_LANG_ACTION, UrlToCommandMapping.USER_PROFILE);
            return UserPage.USER_PROFILE;
        }

        QueryOption disciplinesOption = new QueryOption();
        disciplinesOption.setQueryTemplate(DisciplineDaoQuery.READ_DISCIPLINES_BY_FACULTY_ID);
        disciplinesOption.addParameter(user.getFacultyId());

        List<Discipline> userDisciplines;
        try {
            userDisciplines = DisciplineDAO.getInstance().select(disciplinesOption);
        } catch (DaoException e) {
            throw new LogicException("User disciplines extraction error! Reason - ", e);
        }

        Faculty userFaculty;
        try {
            userFaculty = FacultyDAO.getInstance().read(user.getFacultyId());
        } catch (DaoException e) {
            throw new LogicException("Faculty extraction error! Reason - ", e);
        } finally {
            try {
                ConnectionPool.getInstance().returnConnection();
            } catch (DaoException e) {
                throw new LogicException("Can't close connection! Reason - ", e);
            }
        }
        request.setAttribute(UserParameter.FACULTY_DISCIPLINES, userDisciplines);
        request.setAttribute(UserParameter.FACULTY_NAME, userFaculty.getName());
        request.setAttribute(PageParameter.SWITCH_LANG_ACTION, UrlToCommandMapping.USER_PROFILE);
        return UserPage.USER_PROFILE;
    }
}
