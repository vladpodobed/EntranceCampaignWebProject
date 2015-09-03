package by.epam.training.task06.logic;

import by.epam.training.task06.dao.*;
import by.epam.training.task06.dao.impl.DisciplineDaoMySQL;
import by.epam.training.task06.entity.Certificate;
import by.epam.training.task06.entity.Discipline;
import by.epam.training.task06.entity.User;
import by.epam.training.task06.parameter.UserParameter;
import by.epam.training.task06.util.daoutil.DisciplineDaoQuery;
import by.epam.training.task06.util.daoutil.UserDaoQuery;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *     Provides additional functionality
 * </p>
 */
public class LogicHelp {
    /**
     * <p>
     *     Calculates user certificates points sum
     * </p>
     * @param user
     * @return certificates points sum
     */
    public static int calculateCertificatesSum(User user) {
        int result = 0;
        for(Certificate certificate: user.getCertificates()) {
            result += certificate.getResult();
        }
        result += (int) (user.getSchoolCertificateScore() * 10);
        return result;
    }

    /**
     * <p>
     *     Gets users from database according email and password. If such user doesn't exist returns empty list
     * </p>
     * @param request
     * @return users list
     * @throws LogicException
     */
    public static List<User> getUserFromDB(HttpServletRequest request) throws LogicException {
        String email = request.getParameter(UserParameter.EMAIL);
        String password = request.getParameter(UserParameter.PASSWORD);
        if (null == email || null == password) {
            return null;
        }
        try {
            DaoMySQL<User> dao = DaoMySQLFactory.getInstance().getDao(DaoMySQLType.USER);
            QueryOption option = new QueryOption();
            option.setQueryTemplate(UserDaoQuery.READ_USER_BY_EMAIL_AND_PASSWORD);
            option.addParameter(email);
            option.addParameter(password);
            return dao.select(option);
        } catch (DaoException e) {
            throw new LogicException("Can't load user from DB", e);
        }
    }

    /**
     * <p>
     *     Gets faculty list of disciplines from db according to its name
     * </p>
     * @param facultyName
     * @return list of disciplines
     * @throws LogicException
     */
    public static List<Discipline> getDisciplines(String facultyName) throws LogicException {
        QueryOption disciplinesReadOption = new QueryOption();
        disciplinesReadOption.setQueryTemplate(DisciplineDaoQuery.READ_DISCIPLINES_BY_FACULTY_NAME);
        disciplinesReadOption.addParameter(facultyName);

        List<Discipline> facultyDisciplines;
        try {
            facultyDisciplines = DisciplineDaoMySQL.getInstance().select(disciplinesReadOption);
        } catch (DaoException e) {
            throw new LogicException("User disciplines extraction error! Reason - ", e);
        } finally {
            try {
                ConnectionPool.getInstance().returnConnection();
            } catch (DaoException e) {
                throw new LogicException("Can't close connection! Reason - ", e);
            }
        }
        return facultyDisciplines;
    }
}
