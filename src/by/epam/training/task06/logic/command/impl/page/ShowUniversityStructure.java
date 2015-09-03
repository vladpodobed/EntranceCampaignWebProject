package by.epam.training.task06.logic.command.impl.page;

import by.epam.training.task06.dao.ConnectionPool;
import by.epam.training.task06.dao.DaoException;
import by.epam.training.task06.dao.impl.FacultyDaoMySQL;
import by.epam.training.task06.entity.Faculty;
import by.epam.training.task06.logic.LogicException;
import by.epam.training.task06.logic.UrlToCommandMapping;
import by.epam.training.task06.logic.command.Command;
import by.epam.training.task06.page.UserPage;
import by.epam.training.task06.parameter.FacultyParameter;
import by.epam.training.task06.parameter.PageParameter;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *     Allows show university structure
 * </p>
 */
public class ShowUniversityStructure implements Command {
    @Override
    public String execute(HttpServletRequest request) throws LogicException {
        List<Faculty> faculties;
        try {
            faculties = FacultyDaoMySQL.getInstance().loadAll();
        } catch (DaoException e) {
            throw new LogicException("Faculties load error!");
        } finally {
            try {
                ConnectionPool.getInstance().returnConnection();
            } catch (DaoException e) {
                throw new LogicException("Can't return connection back to pool! Reason - ", e);
            }
        }
        request.setAttribute(FacultyParameter.FACULTIES, faculties);
        request.setAttribute(PageParameter.SWITCH_LANG_ACTION, UrlToCommandMapping.UNIVERSITY_STRUCTURE);
        return UserPage.UNIVERSITY_STRUCTURE;
    }
}
