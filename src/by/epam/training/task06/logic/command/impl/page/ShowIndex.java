package by.epam.training.task06.logic.command.impl.page;

import by.epam.training.task06.exception.LogicException;
import by.epam.training.task06.logic.help.UrlToCommandMapping;
import by.epam.training.task06.logic.command.Command;
import by.epam.training.task06.page.SharedPage;
import by.epam.training.task06.parameter.PageParameter;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *     Allows show index page
 * </p>
 */
public class ShowIndex implements Command {
    @Override
    public String execute(HttpServletRequest request) throws LogicException {
        request.setAttribute(PageParameter.SWITCH_LANG_ACTION, UrlToCommandMapping.INDEX);
        return SharedPage.INDEX;
    }
}
