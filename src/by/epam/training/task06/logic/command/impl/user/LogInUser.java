package by.epam.training.task06.logic.command.impl.user;

import by.epam.training.task06.entity.User;
import by.epam.training.task06.exception.LogicException;
import by.epam.training.task06.logic.help.LogicHelp;
import by.epam.training.task06.logic.command.Command;
import by.epam.training.task06.page.SharedPage;
import by.epam.training.task06.parameter.UserParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *     Allows log in user
 * </p>
 */
public class LogInUser implements Command {
    /**
     * <p>
     *     Gets user from database and set him to a session. In case user doesn't exist returns error message
     * </p>
     * @param request
     * @return index page
     * @throws LogicException
     */
    @Override
    public String execute(HttpServletRequest request) throws LogicException {
        HttpSession session = request.getSession(true);
        List<User> users = LogicHelp.getUserFromDB(request);

        if(users != null && users.size() == 1) {
            session.setAttribute(UserParameter.USER, users.get(0));
        } else {
            request.setAttribute(UserParameter.LOGIN_ERROR, true);
        }
        return SharedPage.INDEX;
    }
}
