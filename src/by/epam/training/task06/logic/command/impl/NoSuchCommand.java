package by.epam.training.task06.logic.command.impl;

import by.epam.training.task06.logic.command.Command;
import by.epam.training.task06.page.SharedPage;
import by.epam.training.task06.parameter.SharedParameter;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *     Command executing when request has bad command parameter.
 * </p>
 */
public class NoSuchCommand implements Command {
    private final static NoSuchCommand instance = new NoSuchCommand();

    private NoSuchCommand() {

    }

    public static NoSuchCommand getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(SharedParameter.ERROR, "unsupported command");
        return SharedPage.ERROR;
    }
}
