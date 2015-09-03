package by.epam.training.task06.logic.command.impl.admin;

import by.epam.training.task06.dao.ConnectionPool;
import by.epam.training.task06.dao.DaoException;
import by.epam.training.task06.dao.QueryOption;
import by.epam.training.task06.dao.impl.DisciplineDaoMySQL;
import by.epam.training.task06.dao.impl.UserDaoMySQL;
import by.epam.training.task06.entity.Discipline;
import by.epam.training.task06.entity.User;
import by.epam.training.task06.logic.LogicException;
import by.epam.training.task06.logic.command.Command;
import by.epam.training.task06.page.AdminPage;
import by.epam.training.task06.parameter.FacultyParameter;
import by.epam.training.task06.parameter.PageParameter;
import by.epam.training.task06.parameter.UserParameter;
import by.epam.training.task06.util.daoutil.DisciplineDaoQuery;
import by.epam.training.task06.util.daoutil.UserDaoQuery;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *     Allows admin to select abiturient from all faculty applicants
 * </p>
 */
public class AdminSelectAbiturient implements Command {
    private static final int allCandidatesSelectValue = 0;
    @Override
    public String execute(HttpServletRequest request) throws LogicException {
        String commandOption = request.getParameter(PageParameter.COMMAND_OPTION);
        String switchLangAction = request.getParameter(PageParameter.SWITCH_LANG_ACTION);
        String candidateIdString = request.getParameter(UserParameter.CANDIDATE_ID);
        String facultyName  = request.getParameter(FacultyParameter.FACULTY_NAME);
        int candidateId = Integer.parseInt(candidateIdString);

        QueryOption facultyIdDisciplineOption = new QueryOption();
        facultyIdDisciplineOption.setQueryTemplate(DisciplineDaoQuery.READ_DISCIPLINES_BY_FACULTY_NAME);
        facultyIdDisciplineOption.addParameter(facultyName);

        List<Discipline> userDisciplines;
        try {
            userDisciplines = DisciplineDaoMySQL.getInstance().select(facultyIdDisciplineOption);
        } catch (DaoException e) {
            throw new LogicException("User disciplines extraction error! Reason - ", e);
        }

        request.setAttribute(PageParameter.COMMAND_OPTION, commandOption);
        request.setAttribute(PageParameter.SWITCH_LANG_ACTION, switchLangAction);
        request.setAttribute(FacultyParameter.FACULTY_NAME, facultyName);
        request.setAttribute(UserParameter.FACULTY_DISCIPLINES, userDisciplines);

        if(allCandidatesSelectValue == candidateId) {
            List<User> abiturients;
            QueryOption statusAndFacultyNameUserOption = new QueryOption();
            statusAndFacultyNameUserOption.setQueryTemplate(UserDaoQuery.READ_USER_BY_STATUS_AND_FACULTY_NAME);
            statusAndFacultyNameUserOption.addParameter(UserParameter.DOCUMENTS_PROCCESSING_STATUS);
            statusAndFacultyNameUserOption.addParameter(facultyName);

            try {
                abiturients = UserDaoMySQL.getInstance().select(statusAndFacultyNameUserOption);
            } catch (DaoException e) {
                throw new LogicException("Candidates read error! Reason - " + e.getMessage());
            }

            for(User abiturient: abiturients) {
                abiturient.setStatus(UserParameter.ABITURIENT_STATUS);
                try {
                    UserDaoMySQL.getInstance().update(abiturient);
                } catch (DaoException e) {
                    throw new LogicException("Abiturient update error! Reason - " + e.getMessage());
                }
            }
            return AdminPage.SELECT_ABITURIENT;
        }

        User user;
        try {
            user = UserDaoMySQL.getInstance().read(candidateId);
        } catch (DaoException e) {
            throw new LogicException("Candidate read error! Reason - " + e.getMessage());
        }

        user.setStatus(UserParameter.ABITURIENT_STATUS);
        try {
            UserDaoMySQL.getInstance().update(user);
        } catch (DaoException e) {
            throw new LogicException("Candidate update error! Reason - " + e.getMessage());
        }

        QueryOption statusAndFacultyIdUserOption = new QueryOption();
        statusAndFacultyIdUserOption.setQueryTemplate(UserDaoQuery.READ_USER_BY_STATUS_AND_FACULTY_NAME);
        statusAndFacultyIdUserOption.addParameter(UserParameter.DOCUMENTS_PROCCESSING_STATUS);
        statusAndFacultyIdUserOption.addParameter(facultyName);

        List<User> candidates;
        try {
            candidates = UserDaoMySQL.getInstance().select(statusAndFacultyIdUserOption);
        } catch (DaoException e) {
            throw new LogicException("Can't get candidates! Reason - " + e.getMessage());
        } finally {
            try {
                ConnectionPool.getInstance().returnConnection();
            } catch (DaoException e) {
                throw new LogicException("Can't close connection! Reason - ", e);
            }
        }
        request.setAttribute(UserParameter.CANDIDATES, candidates);
        return AdminPage.SELECT_ABITURIENT;
    }
}
