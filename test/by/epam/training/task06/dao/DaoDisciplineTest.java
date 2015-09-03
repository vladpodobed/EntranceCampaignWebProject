package by.epam.training.task06.dao;

import by.epam.training.task06.dao.impl.DisciplineDaoMySQL;
import by.epam.training.task06.entity.Discipline;
import by.epam.training.task06.util.daoutil.DisciplineDaoQuery;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.fail;


public class DaoDisciplineTest {

    private List<Discipline> disciplines = null;
    private Discipline discipline = null;

    @Test
    public void daoReDAllTest() {
        try {
            disciplines = DisciplineDaoMySQL.getInstance().loadAll();
        } catch (DaoException e) {
            fail("ReadAll FacultyDaoMySQL method fails. Reason - " + e);
        }
        System.out.println("Read all test:\n" + disciplines);
    }

    @Test
    public void daoReadTest() {
        int id = 1;
        try {
            discipline = DisciplineDaoMySQL.getInstance().read(id);
        } catch (DaoException e) {
            fail("Read DisciplineDaoMySQL method fails. Reason - " + e);
        }
        System.out.println("Read test id-" + id + ":\n" + discipline);
    }

    @Test
    public void daoReadByTest() {
        String faculty_name = "Факультет информационных технологий";
        QueryOption option = new QueryOption();
        option.setQueryTemplate(DisciplineDaoQuery.READ_DISCIPLINES_BY_FACULTY_NAME);
        option.addParameter(faculty_name);
        try {
            disciplines = DisciplineDaoMySQL.getInstance().select(option);
        } catch (DaoException e) {
            fail("ReadBy FacultyDaoMySQL method fails. Reason - " + e);
        }
        System.out.println("Read by test:\n" + disciplines);
    }


}
