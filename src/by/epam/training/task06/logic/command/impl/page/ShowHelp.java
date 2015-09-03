package by.epam.training.task06.logic.command.impl.page;

import by.epam.training.task06.logic.LogicException;
import by.epam.training.task06.logic.UrlToCommandMapping;
import by.epam.training.task06.logic.command.Command;
import by.epam.training.task06.page.UserPage;
import by.epam.training.task06.parameter.PageParameter;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *     Allows show user help page
 * </p>
 */
public class ShowHelp implements Command {
    @Override
    public String execute(HttpServletRequest request) throws LogicException {
        request.setAttribute(PageParameter.SWITCH_LANG_ACTION, UrlToCommandMapping.HELP);
        return UserPage.HELP;
    }
}
