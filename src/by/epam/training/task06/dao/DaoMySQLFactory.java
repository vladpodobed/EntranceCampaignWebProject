package by.epam.training.task06.dao;

import by.epam.training.task06.dao.impl.CertificateDaoMySQL;
import by.epam.training.task06.dao.impl.DisciplineDaoMySQL;
import by.epam.training.task06.dao.impl.FacultyDaoMySQL;
import by.epam.training.task06.dao.impl.UserDaoMySQL;


public class DaoMySQLFactory {
    private static final DaoMySQLFactory instance = new DaoMySQLFactory();

    private DaoMySQLFactory() {

    }

    public static DaoMySQLFactory getInstance() {
        return instance;
    }

    public DaoMySQL getDao(DaoMySQLType type) throws DaoException {
        switch (type) {
            case CERTIFICATE:
                return CertificateDaoMySQL.getInstance();
            case DISCIPLINE:
                return DisciplineDaoMySQL.getInstance();
            case FACULTY:
                return FacultyDaoMySQL.getInstance();
            case USER:
                return UserDaoMySQL.getInstance();
            default:
                throw new DaoException("No such dao!");
        }
    }
}



