package by.epam.training.task06.util.daoutil;

/**
 * <p>
 *     Contains special db query templates
 * </p>
 */
public class UserDaoQuery {
    public static final String READ_USER_BY_STATUS = "SELECT * FROM university.site_user WHERE status=?;";
    public static final String READ_USER_BY_FACULTY_ID = "SELECT * FROM university.site_user WHERE faculty_id=?;";
    public static final String READ_USER_BY_STATUS_AND_FACULTY_ID = "SELECT * FROM university.site_user WHERE status=? AND faculty_id=?;";
    public static final String READ_USER_BY_EMAIL_AND_PASSWORD = "SELECT * FROM university.site_user WHERE email=? AND password=?";
    public static final String READ_USER_BY_FACULTY_NAME = "SELECT site_user.user_id, site_user.status, site_user.email, site_user.password, site_user.full_name, site_user.certificate_score, site_user.faculty_id FROM university.site_user JOIN university.faculty on site_user.faculty_id=faculty.faculty_id WHERE faculty.name=?;";
    public static final String READ_USER_BY_STATUS_AND_FACULTY_NAME = "SELECT site_user.user_id, site_user.status, site_user.email, site_user.password, site_user.full_name, site_user.certificate_score, site_user.faculty_id FROM university.site_user JOIN university.faculty on site_user.faculty_id=faculty.faculty_id WHERE site_user.status=? AND faculty.name=?;";

    private UserDaoQuery() {

    }
}
