package by.epam.training.task06.logic.command.impl.page;

import by.epam.training.task06.logic.LogicException;
import by.epam.training.task06.logic.UrlToCommandMapping;
import by.epam.training.task06.logic.command.Command;
import by.epam.training.task06.parameter.PageParameter;
import by.epam.training.task06.parameter.SharedParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *     Allows language switching from any page
 * </p>
 */
public class SwitchLang implements Command {
    @Override
    public String execute(HttpServletRequest request) throws LogicException {
        HttpSession session = request.getSession(true);

        String lang = request.getParameter(SharedParameter.LANG);
        switch (lang) {
            case "ru":
                lang = "en";
                break;
            case "en":
                lang = "ru";
                break;
            default:
                throw new LogicException("Can't find lang argument");
        }

        String referer = request.getHeader(PageParameter.REFERER);
        String url = referer.substring(referer.lastIndexOf("/"), referer.length());
        if(url.equals("/")) {
            url = "/index";
        }

        request.setAttribute(PageParameter.SWITCH_LANG_ACTION, url);
        session.setAttribute(SharedParameter.BUNDLE, SharedParameter.BUNDLE_LANG + lang);
        return UrlToCommandMapping.getInstance().getCommand(url).execute(request);
    }
}
