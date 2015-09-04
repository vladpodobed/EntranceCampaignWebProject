package by.epam.training.task06.controller;

import by.epam.training.task06.exception.LogicException;
import by.epam.training.task06.logic.command.Command;
import by.epam.training.task06.logic.command.CommandHandler;
import by.epam.training.task06.page.SharedPage;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Controller extends HttpServlet {
    private static final Logger log = Logger.getLogger(Controller.class);
    private static final String COMMAND = "command";

    /**
     * <p>
     *      Gets command from request which comes via get-request
     * </p>
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    /**
     * <p>
     *      Get command from request which comes vis post-request
     * </p>
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    /**
     * <p>
     *     Gets command from request and tries to execute it
     * </p>
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = CommandHandler.getInstance().getCommand(request.getParameter(COMMAND));
        String page;

        try {
            page = command.execute(request);
        } catch (LogicException e) {
            log.error("Error when executing command! " + e);
            request.setAttribute("error", e.getMessage());
            page = SharedPage.ERROR;
        }
        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServletException | IOException e) {
            log.error("Error when forwarding page - " + page);
        }
    }
}


