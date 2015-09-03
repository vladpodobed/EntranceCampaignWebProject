package by.epam.training.task06.page;

import by.epam.training.task06.util.routingutil.RoutingUtil;

/**
 * <p>
 *     Contains path to user pages
 * </p>
 */
public class UserPage {
    public static final String SIGN_UP = RoutingUtil.getRoutingBundle().getString("user.sign_up");
    public static final String UNIVERSITY_STRUCTURE = RoutingUtil.getRoutingBundle().getString("user.university_structure");
    public static final String HELP = RoutingUtil.getRoutingBundle().getString("user.help");
    public static final String REGISTER_AT_FACULTY = RoutingUtil.getRoutingBundle().getString("user.register_at_faculty");
    public static final String USER_PROFILE = RoutingUtil.getRoutingBundle().getString("user.profile");
    public static final String UPDATE_USER_PROFILE = RoutingUtil.getRoutingBundle().getString("user.update_profile");
    public static final String SELECT_FACULTY = RoutingUtil.getRoutingBundle().getString("user.select_faculty");

    private UserPage() {

    }
}
