package by.epam.training.task06.util.daoutil;

/**
 * <p>
 *     Contains special db query templates
 * </p>
 */
public final class CertificateDaoQuery {
    public static final String READ_CERTIFICATE_BY_USER_ID = "SELECT * FROM university.certificate WHERE user_id=?;";

    private CertificateDaoQuery() {

    }
}
