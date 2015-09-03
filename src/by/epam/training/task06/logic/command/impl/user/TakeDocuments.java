package by.epam.training.task06.logic.command.impl.user;

import by.epam.training.task06.dao.ConnectionPool;
import by.epam.training.task06.dao.DaoException;
import by.epam.training.task06.dao.impl.CertificateDaoMySQL;
import by.epam.training.task06.dao.impl.UserDaoMySQL;
import by.epam.training.task06.entity.Certificate;
import by.epam.training.task06.entity.User;
import by.epam.training.task06.logic.LogicException;
import by.epam.training.task06.logic.command.Command;
import by.epam.training.task06.page.SharedPage;
import by.epam.training.task06.parameter.UserParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *     Allows user to take documents back from the faculty. When user takes back his documents he is getting visitor status
 * </p>
 */
public class TakeDocuments implements Command {
    @Override
    public String execute(HttpServletRequest request) throws LogicException {
        HttpSession session = request.getSession(false);
        if(null == session) {
            throw new LogicException("Can't get session!");
        }

        User user = (User) session.getAttribute(UserParameter.USER);

        if(user.getCertificates() != null) {
            for (Certificate certificate : user.getCertificates()) {
                try {
                    CertificateDaoMySQL.getInstance().delete(certificate.getId());
                } catch (DaoException e) {
                    throw new LogicException("Can't delete user certificate! Reason - " + e.getMessage());
                }
            }
        }

        user.setStatus(UserParameter.VISITOR_STATUS);
        user.setCertificates(null);
        user.setSchoolCertificateScore(0.0);
        user.setFacultyId(0);

        try {
            UserDaoMySQL.getInstance().update(user);
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
}
