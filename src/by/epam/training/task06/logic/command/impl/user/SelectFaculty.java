package by.epam.training.task06.logic.command.impl.user;

import by.epam.training.task06.logic.CommandOptionToActionMapping;
import by.epam.training.task06.logic.LogicException;
import by.epam.training.task06.logic.command.Command;
import by.epam.training.task06.logic.command.impl.admin.AdminSelectFacultyForSelectAbiturient;
import by.epam.training.task06.logic.command.impl.admin.AdminSelectFacultyForStatistics;
import by.epam.training.task06.page.SharedPage;
import by.epam.training.task06.parameter.PageParameter;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *     Allows select faculty for different needs: selecting abiturients, showing faculty statistics, user registration at faculty
 * </p>
 */
public class SelectFaculty implements Command {
    @Override
    public String execute(HttpServletRequest request) throws LogicException {
        String commandOption = request.getParameter(PageParameter.COMMAND_OPTION);
        switch(commandOption) {
            case CommandOptionToActionMapping.ADMIN_FACULTY_STATISTICS:
                return new AdminSelectFacultyForStatistics().execute(request);
            case CommandOptionToActionMapping.ADMIN_SELECT_ABITURIENT:
                return new AdminSelectFacultyForSelectAbiturient().execute(request);
            case CommandOptionToActionMapping.USER_REGISTER_AT_FACULTY:
                return new SelectFacultyForRegistration().execute(request);
            default:
                request.setAttribute(PageParameter.ERROR, "Bad command option when selecting a faculty!");
                return SharedPage.ERROR;
        }
    }
}
