package by.epam.training.task06.constants.query_template;

/**
 * <p>
 *     Contains special db query templates
 * </p>
 */
public class DisciplineDaoQuery {
    public static final String READ_DISCIPLINES_BY_FACULTY_ID = "SELECT discipline.discipline_id, discipline.name FROM university.discipline JOIN university.discipline_for_faculty on discipline.discipline_id=discipline_for_faculty.discipline_id WHERE discipline_for_faculty.faculty_id=?;";
    public static final String READ_DISCIPLINES_BY_FACULTY_NAME = "SELECT discipline.discipline_id, discipline.name FROM (university.discipline JOIN university.discipline_for_faculty on discipline.discipline_id=discipline_for_faculty.discipline_id) JOIN university.faculty on faculty.faculty_id=discipline_for_faculty.faculty_id WHERE faculty.name=?;";

    private DisciplineDaoQuery() {

    }
}
