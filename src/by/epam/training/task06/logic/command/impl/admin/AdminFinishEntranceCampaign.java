package by.epam.training.task06.logic.command.impl.admin;

import by.epam.training.task06.dao.*;
import by.epam.training.task06.dao.impl.FacultyDAO;
import by.epam.training.task06.dao.query.QueryOption;
import by.epam.training.task06.entity.Faculty;
import by.epam.training.task06.entity.User;
import by.epam.training.task06.exception.DaoException;
import by.epam.training.task06.exception.LogicException;
import by.epam.training.task06.logic.command.Command;
import by.epam.training.task06.page.SharedPage;
import by.epam.training.task06.parameter.UserParameter;
import by.epam.training.task06.util.comparator.AbiturientsScoreComparator;
import by.epam.training.task06.constants.query_template.UserDaoQuery;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *     Allows admin to finish entrance campaign
 *     Finds out abiturients with highest certificates sum and makes them students
 *     Other abiturients are getting not enrolled
 * </p>
 */
public class AdminFinishEntranceCampaign implements Command {
    /**
     * <p>
     *     Finds out abiturints with highest certificates sum and makes them students
     *     Other abiturients are getting not enrolled
     * </p>
     * @param request
     * @return index page
     * @throws LogicException
     */
    @Override
    public String execute(HttpServletRequest request) throws LogicException {
        HttpSession session = request.getSession(false);
        if (null == session){
            throw new LogicException("Can't get session!");
        }

        DAO<User> userDao;
        try {
            userDao = DAOFactory.getInstance().getDao(DAOType.USER);
        } catch (DaoException e) {
            throw new LogicException("Can't get dao object! Reason - " + e.getMessage());
        }

        List<Faculty> faculties;
        try {
            faculties = FacultyDAO.getInstance().loadAll();
        } catch (DaoException e) {
            throw new LogicException("Can't load all faculties! Reason - " + e.getMessage());
        }

        QueryOption abiturientStatusAndFacultyNameOption = new QueryOption();
        abiturientStatusAndFacultyNameOption.setQueryTemplate(UserDaoQuery.READ_USER_BY_STATUS_AND_FACULTY_NAME);

        List<User> facultyAbiturients;
        for(Faculty faculty: faculties) {
            abiturientStatusAndFacultyNameOption.clearParametersList();
            abiturientStatusAndFacultyNameOption.addParameter(UserParameter.ABITURIENT_STATUS);
            abiturientStatusAndFacultyNameOption.addParameter(faculty.getName());

            try {
                facultyAbiturients = userDao.select(abiturientStatusAndFacultyNameOption);
            } catch (DaoException e) {
                throw new LogicException("Can't load faculty abiturients! Reason - " + e.getMessage());
            }

            List<User> students = calculateStudents(faculty, facultyAbiturients);
            try {
                updateUsersStatus(userDao, students, UserParameter.STUDENT_STATUS);
                facultyAbiturients.removeAll(students);
                updateUsersStatus(userDao, facultyAbiturients, UserParameter.NOT_ENROLLED_STATUS);
            } catch (DaoException e) {
                throw new LogicException("Can't update students status! Reason - " + e.getMessage());
            }

        }

        List<User> notEnrolled;
        QueryOption notEnrolledStatusOption = new QueryOption();
        notEnrolledStatusOption.setQueryTemplate(UserDaoQuery.READ_USER_BY_STATUS);
        notEnrolledStatusOption.addParameter(UserParameter.DOCUMENTS_PROCCESSING_STATUS);
        try {
            notEnrolled = userDao.select(notEnrolledStatusOption);
            updateUsersStatus(userDao, notEnrolled, UserParameter.NOT_ENROLLED_STATUS);
        } catch (DaoException e) {
            throw new LogicException("Can't update not enrolled status! Reason - " + e.getMessage());
        }
        session.setAttribute("finishCampaign", true);
        return SharedPage.INDEX;
    }

    /**
     * <p>
     *     Calculates abiturients with highest points sum at faculty
     * </p>
     * @param faculty
     * @param abiturients
     * @return students list
     * @throws LogicException
     */
    public static List<User> calculateStudents(Faculty faculty, List<User> abiturients) throws LogicException {
        List<User> result = new LinkedList<>();

        List<User> abitur = new LinkedList<>();
        abitur.addAll(abiturients);
        Collections.sort(abitur, new AbiturientsScoreComparator());
        if (abitur.size() > faculty.getEnrollment()) {
            result.addAll(abitur.subList(0, (faculty.getEnrollment())));
        } else {
            result.addAll(abitur);
        }
        return result;
    }

    /**
     * <p>
     *     Sets status for users using dao
     * </p>
     * @param dao
     * @param users
     * @param status
     * @throws DaoException
     */
    private void updateUsersStatus(DAO dao, List<User> users, String status) throws DaoException {
        for (User user : users) {
            user.setStatus(status);
            dao.update(user);
        }
    }

}
