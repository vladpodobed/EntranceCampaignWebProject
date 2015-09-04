package by.epam.training.task06.logic.command.impl.user;

import by.epam.training.task06.connection.ConnectionPool;
import by.epam.training.task06.dao.*;
import by.epam.training.task06.dao.impl.CertificateDAO;
import by.epam.training.task06.dao.impl.DisciplineDAO;
import by.epam.training.task06.dao.impl.UserDAO;
import by.epam.training.task06.dao.query.QueryOption;
import by.epam.training.task06.entity.Certificate;
import by.epam.training.task06.entity.Discipline;
import by.epam.training.task06.entity.User;
import by.epam.training.task06.exception.DaoException;
import by.epam.training.task06.exception.LogicException;
import by.epam.training.task06.logic.help.UrlToCommandMapping;
import by.epam.training.task06.logic.command.Command;
import by.epam.training.task06.page.SharedPage;
import by.epam.training.task06.page.UserPage;
import by.epam.training.task06.parameter.FacultyParameter;
import by.epam.training.task06.parameter.PageParameter;
import by.epam.training.task06.parameter.UserParameter;
import by.epam.training.task06.constants.query_template.DisciplineDaoQuery;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * Allows user registration in the system
 * </p>
 */
public class RegisterAtFaculty implements Command {
    @Override
    public String execute(HttpServletRequest request) throws LogicException {
        HttpSession session = request.getSession(false);
        if (null == session) {
            throw new LogicException("Can't get session!");
        }

        String facultyName = request.getParameter(FacultyParameter.FACULTY_NAME);
        String commandOption = request.getParameter(PageParameter.COMMAND_OPTION);

        User user = (User) session.getAttribute(UserParameter.USER);

        QueryOption disciplinesOption = new QueryOption();
        disciplinesOption.setQueryTemplate(DisciplineDaoQuery.READ_DISCIPLINES_BY_FACULTY_ID);
        disciplinesOption.addParameter(getFacultyIdByFacultyName(facultyName));

        List<Discipline> facultyDisciplines;
        try {
            facultyDisciplines = DisciplineDAO.getInstance().select(disciplinesOption);
        } catch (DaoException e) {
            throw new LogicException("User disciplines extraction error! Reason - ", e);
        }

        user.setFacultyId(getFacultyIdByFacultyName(facultyName));

        String firstScore = request.getParameter(facultyDisciplines.get(0).getName());
        String secondScore = request.getParameter(facultyDisciplines.get(1).getName());
        String thirdScore = request.getParameter(facultyDisciplines.get(2).getName());
        String schoolScore = request.getParameter(UserParameter.SCHOOL_CERTIFICATE);
        if (firstScore.isEmpty() || secondScore.isEmpty() || thirdScore.isEmpty() || schoolScore.isEmpty()) {
            session.setAttribute(UserParameter.USER, user);
            request.setAttribute(UserParameter.INPUT_ERROR, true);
            request.setAttribute(UserParameter.FACULTY_DISCIPLINES, facultyDisciplines);
            request.setAttribute(FacultyParameter.FACULTY_NAME, facultyName);
            request.setAttribute(PageParameter.COMMAND_OPTION, commandOption);
            request.setAttribute(PageParameter.SWITCH_LANG_ACTION, UrlToCommandMapping.REGISTER_AT_FACULTY);
            return UserPage.REGISTER_AT_FACULTY;
        }

        int firstCertificateScore;
        int secondCertificateScore;
        int thirdCertificateScore;
        double schoolCertificateScore;

        try {
            firstCertificateScore = Integer.parseInt(firstScore);
            secondCertificateScore = Integer.parseInt(secondScore);
            thirdCertificateScore = Integer.parseInt(thirdScore);
            schoolCertificateScore = Double.parseDouble(schoolScore);
        } catch (NumberFormatException e) {
            throw new LogicException("Can't get certificate scores! Reason - ", e);
        }

        List<Certificate> userCertificates = new LinkedList<>();
        Certificate firstCertificate;
        Certificate secondCertificate;
        Certificate thirdCertificate;

        if (user.getStatus().equals(UserParameter.VISITOR_STATUS)) {
            firstCertificate = createCertificate(user.getId(), facultyDisciplines.get(0).getId(), firstCertificateScore);
            secondCertificate = createCertificate(user.getId(), facultyDisciplines.get(1).getId(), secondCertificateScore);
            thirdCertificate = createCertificate(user.getId(), facultyDisciplines.get(2).getId(), thirdCertificateScore);
        } else {
            firstCertificate = new Certificate(user.getCertificates().get(0).getId(), user.getId(), facultyDisciplines.get(0).getId(), firstCertificateScore);
            secondCertificate = new Certificate(user.getCertificates().get(1).getId(), user.getId(), facultyDisciplines.get(1).getId(), secondCertificateScore);
            thirdCertificate = new Certificate(user.getCertificates().get(2).getId(), user.getId(), facultyDisciplines.get(2).getId(), thirdCertificateScore);
        }

        userCertificates.add(firstCertificate);
        userCertificates.add(secondCertificate);
        userCertificates.add(thirdCertificate);

        user.setCertificates(userCertificates);
        user.setSchoolCertificateScore(schoolCertificateScore);
        user.setStatus(UserParameter.DOCUMENTS_PROCCESSING_STATUS);

        try {
            UserDAO.getInstance().update(user);
            for (Certificate certificate : userCertificates) {
                CertificateDAO.getInstance().update(certificate);
            }
        } catch (DaoException e) {
            throw new LogicException("Can't update user info! Reason - " + e.getMessage());
        } finally {
            try {
                ConnectionPool.getInstance().returnConnection();
            } catch (DaoException e) {
                throw new LogicException("Can't return connection! Reason - ", e);
            }
        }
        session.setAttribute(UserParameter.USER, user);
        return SharedPage.INDEX;
    }

    public int getFacultyIdByFacultyName(String facultyName) {
        int facultyId;
        switch (facultyName) {
            case "Экономический факультет":
                facultyId = 1;
                break;
            case "Физический факультет":
                facultyId = 2;
                break;
            case "Химический факультет":
                facultyId = 3;
                break;
            case "Факультет информационных технологий":
                facultyId = 4;
                break;
            default:
                facultyId = 0;
        }
        return facultyId;
    }

    private Certificate createCertificate(int userId, int disciplineId, int result) throws LogicException {
        Certificate certificate;
        DAO<Certificate> dao;
        try {
            dao = DAOFactory.getInstance().getDao(DAOType.CERTIFICATE);
            certificate = new Certificate(userId, disciplineId, result);
            certificate.setId(dao.create(certificate));
        } catch (DaoException e) {
            throw new LogicException("Can't create certificate! Reason - ", e);
        }
        return certificate;
    }
}
