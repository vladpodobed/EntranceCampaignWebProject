package by.epam.training.task06.page;

import by.epam.training.task06.util.routing.RoutingUtil;

/**
 * <p>
 *     Contains path to admin pages
 * </p>
 */
public class AdminPage {
    public static final String SELECT_ABITURIENT = RoutingUtil.getRoutingBundle().getString("admin.select_abiturient");
    public static final String FACULTY_STATISTICS = RoutingUtil.getRoutingBundle().getString("admin.faculty_statistics");

    private AdminPage() {

    }
}
