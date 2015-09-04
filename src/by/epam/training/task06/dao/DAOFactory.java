package by.epam.training.task06.dao;

import by.epam.training.task06.dao.impl.CertificateDAO;
import by.epam.training.task06.dao.impl.DisciplineDAO;
import by.epam.training.task06.dao.impl.FacultyDAO;
import by.epam.training.task06.dao.impl.UserDAO;
import by.epam.training.task06.exception.DaoException;


public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public DAO getDao(DAOType type) throws DaoException {
        switch (type) {
            case CERTIFICATE:
                return CertificateDAO.getInstance();
            case DISCIPLINE:
                return DisciplineDAO.getInstance();
            case FACULTY:
                return FacultyDAO.getInstance();
            case USER:
                return UserDAO.getInstance();
            default:
                throw new DaoException("No such dao!");
        }
    }
}



