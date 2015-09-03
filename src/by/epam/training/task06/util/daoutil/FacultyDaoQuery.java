package by.epam.training.task06.util.daoutil;

/**
 * <p>
 *     Contains special db query templates
 * </p>
 */
public class FacultyDaoQuery {
    public static final String READ_FACULTY_BY_NAME = "SELECT * FROM university.faculty WHERE name=?;";

    private FacultyDaoQuery() {

    }
}
