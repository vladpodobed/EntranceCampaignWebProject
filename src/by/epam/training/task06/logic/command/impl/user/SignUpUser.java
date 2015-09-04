package by.epam.training.task06.logic.command.impl.user;

import by.epam.training.task06.connection.ConnectionPool;
import by.epam.training.task06.dao.*;
import by.epam.training.task06.entity.User;
import by.epam.training.task06.exception.DaoException;
import by.epam.training.task06.exception.LogicException;
import by.epam.training.task06.logic.help.LogicHelp;
import by.epam.training.task06.logic.help.UrlToCommandMapping;
import by.epam.training.task06.logic.command.Command;
import by.epam.training.task06.page.SharedPage;
import by.epam.training.task06.page.UserPage;
import by.epam.training.task06.parameter.PageParameter;
import by.epam.training.task06.parameter.UserParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *     Allows sign user up
 * </p>
 */
public class SignUpUser implements Command {
    @Override
    public String execute(HttpServletRequest request) throws LogicException {
        String email = request.getParameter(UserParameter.EMAIL);
        String password = request.getParameter(UserParameter.PASSWORD);
        String name = request.getParameter(UserParameter.NAME);

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            request.setAttribute(UserParameter.INPUT_ERROR, true);
            request.setAttribute(PageParameter.SWITCH_LANG_ACTION, UrlToCommandMapping.SIGN_UP_FORM);
            return UserPage.SIGN_UP;
        }

        List<User> users = LogicHelp.getUserFromDB(request);
        User user;

        if (users.size() == 0) {
            user = createUser(UserParameter.VISITOR_STATUS, email, password, name);
        } else {
            request.setAttribute(UserParameter.INPUT_ERROR, true);
            request.setAttribute(PageParameter.SWITCH_LANG_ACTION, UrlToCommandMapping.SIGN_UP_FORM);
            return UserPage.SIGN_UP;
        }
        HttpSession session = request.getSession(true);
        session.setAttribute(UserParameter.USER, user);
        return SharedPage.INDEX;
    }

    /**
     * <p>
     * Create user object in database
     * </p>
     *
     * @param status
     * @param email
     * @param password
     * @param name
     * @return user
     * @throws LogicException
     */
    private User createUser(String status, String email, String password, String name) throws LogicException {
        User user;
        DAO<User> dao;
        try {
            dao = DAOFactory.getInstance().getDao(DAOType.USER);
            user = new User(status, email, password, name);
            user.setId(dao.create(user));
        } catch (DaoException e) {
            throw new LogicException("can't create user:" + e.getMessage(), e);
        } finally {
            try {
                ConnectionPool.getInstance().returnConnection();
            } catch (DaoException e) {
                throw new LogicException("Can't return connection! Reason - ", e);
            }
        }
        return user;
    }
}
