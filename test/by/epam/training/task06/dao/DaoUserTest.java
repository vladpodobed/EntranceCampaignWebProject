package by.epam.training.task06.dao;

import by.epam.training.task06.dao.impl.UserDAO;
import by.epam.training.task06.dao.query.QueryOption;
import by.epam.training.task06.entity.User;
import by.epam.training.task06.exception.DaoException;
import by.epam.training.task06.parameter.UserParameter;
import by.epam.training.task06.constants.query_template.UserDaoQuery;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.fail;

/**
 * Created by higgs on 23.05.15.
 */
public class DaoUserTest {

    private List<User> users = null;
    private User user = null;

    @Test
    public void daoLoadAllTest() {
        try {
            users = UserDAO.getInstance().loadAll();
        } catch (DaoException e) {
            fail("LoadAll FacultyDaoMySQL method fails. Reason - " + e);
        }
        System.out.println("\nRead all test:\n" + users + "\n\n");
    }

    @Test
    public void daoReadTest() {
        int id = 3;
        try {
            user = UserDAO.getInstance().read(id);
        } catch (DaoException e) {
            fail("LoadAll FacultyDaoMySQL method fails. Reason - " + e);
        }
        System.out.println("\nRead test id-" + id + ":\n" + user + "\n\n");
    }

    @Test
    public void daoReadByTest() {
        String facultyName = "Химический факультет";

        QueryOption option = new QueryOption();
        option.setQueryTemplate(UserDaoQuery.READ_USER_BY_STATUS_AND_FACULTY_NAME);
        option.addParameter(UserParameter.ABITURIENT_STATUS);
        option.addParameter(facultyName);

        try {
            users = UserDAO.getInstance().select(option);
        } catch (DaoException e) {
            fail("LoadAll FacultyDaoMySQL method fails. Reason - " + e);
        }
        System.out.println("\nRead by test:\n" + users + "\n\n");
    }


}
