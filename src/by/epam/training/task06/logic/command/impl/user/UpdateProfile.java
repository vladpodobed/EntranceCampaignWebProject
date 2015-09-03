package by.epam.training.task06.logic.command.impl.user;


import by.epam.training.task06.dao.*;
import by.epam.training.task06.dao.impl.UserDaoMySQL;
import by.epam.training.task06.entity.User;
import by.epam.training.task06.logic.LogicException;
import by.epam.training.task06.logic.UrlToCommandMapping;
import by.epam.training.task06.logic.command.Command;
import by.epam.training.task06.page.SharedPage;
import by.epam.training.task06.page.UserPage;
import by.epam.training.task06.parameter.PageParameter;
import by.epam.training.task06.parameter.UserParameter;
import by.epam.training.task06.util.daoutil.UserDaoQuery;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


public class UpdateProfile implements Command {
    @Override
    public String execute(HttpServletRequest request) throws LogicException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new LogicException("Can't get session!");
        }

        User user = (User) session.getAttribute(UserParameter.USER);

        String name = request.getParameter(UserParameter.NAME);
        String email = request.getParameter(UserParameter.EMAIL);
        String password = request.getParameter(UserParameter.PASSWORD);

        request.setAttribute(PageParameter.SWITCH_LANG_ACTION, UrlToCommandMapping.UPDATE_PROFILE);

        if(name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            request.setAttribute(UserParameter.INPUT_ERROR, true);
            return UserPage.UPDATE_USER_PROFILE;
        }

        List<User> users = getExistingUser(request);

        if(users.size() == 0) {
            setBasicUserInformation(user, name, email, password);
        } else if(users.size() == 1) {
            if(users.get(0).equals(user)) {
                setBasicUserInformation(user, name, email, password);
            } else {
                request.setAttribute(UserParameter.INPUT_ERROR, true);
                return UserPage.UPDATE_USER_PROFILE;
            }
        } else {
            request.setAttribute(UserParameter.INPUT_ERROR, true);
            return UserPage.UPDATE_USER_PROFILE;
        }
        session.setAttribute(UserParameter.USER, user);
        return SharedPage.INDEX;
    }

    private void setBasicUserInformation(User user, String name, String email, String password) throws LogicException {
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        updateUser(user);
    }

    private List<User> getExistingUser(HttpServletRequest request) throws LogicException {
        String email = request.getParameter(UserParameter.EMAIL);
        String password = request.getParameter(UserParameter.PASSWORD);
        if (null == email || null == password) {
            return null;
        }

        try {
            DaoMySQL<User> dao = DaoMySQLFactory.getInstance().getDao(DaoMySQLType.USER);
            QueryOption option = new QueryOption();
            option.setQueryTemplate(UserDaoQuery.READ_USER_BY_EMAIL_AND_PASSWORD);
            option.addParameter(email);
            option.addParameter(password);
            List<User> users = dao.select(option);
            return users;
        } catch (DaoException e) {
            throw new LogicException("Can't load user from DB", e);
        }
    }

    private void updateUser(User user) throws LogicException {
        try {
            UserDaoMySQL.getInstance().update(user);
        } catch (DaoException e) {
            throw new LogicException("Can't update user! Reason - ", e);
        } finally {
            try {
                ConnectionPool.getInstance().returnConnection();
            } catch (DaoException e) {
                throw new LogicException("Can't close connection! Reason - ", e);
            }
        }
    }
}
