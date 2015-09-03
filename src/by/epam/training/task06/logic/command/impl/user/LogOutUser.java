package by.epam.training.task06.logic.command.impl.user;

import by.epam.training.task06.logic.command.Command;
import by.epam.training.task06.logic.LogicException;
import by.epam.training.task06.page.SharedPage;
import by.epam.training.task06.parameter.UserParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *     Allows log out user
 * </p>
 */
public class LogOutUser implements Command {
    @Override
    public String execute(HttpServletRequest request) throws LogicException {
        HttpSession session= request.getSession(false);
        if (null != session){
            session.removeAttribute(UserParameter.USER);
        }
        return SharedPage.INDEX;
    }
}
