package by.epam.training.task06.dao;

import by.epam.training.task06.dao.impl.FacultyDaoMySQL;
import by.epam.training.task06.entity.Faculty;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.fail;

/**
 * Created by higgs on 23.05.15.
 */
public class DaoFacultyTest {

    private List<Faculty> faculties = null;
    private Faculty faculty = null;

    @Test
    public void daoLoadAllTest() {
        try {
            faculties = FacultyDaoMySQL.getInstance().loadAll();
        } catch (DaoException e) {
            fail("LoadAll FacultyDaoMySQL method fails. Reason - " + e);
        }

        System.out.println("Read all test:\n" + faculties);
    }

    @Test
    public void daoReadTest() {
        int id = 1;
        try {

            faculty = FacultyDaoMySQL.getInstance().read(id);
        } catch (DaoException e) {
            fail("LoadAll FacultyDaoMySQL method fails. Reason - " + e);
        }

        System.out.println("Read test id-" + id + ":\n" + faculty);
    }
}
