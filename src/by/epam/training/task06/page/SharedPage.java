package by.epam.training.task06.page;

import by.epam.training.task06.util.routing.RoutingUtil;

/**
 * <p>
 *     Contains path to common pages
 * </p>
 */
public class SharedPage {
    public static final String INDEX = RoutingUtil.getRoutingBundle().getString("page.index");
    public static final String ERROR = RoutingUtil.getRoutingBundle().getString("page.error");
    public static final String ERROR_404 = RoutingUtil.getRoutingBundle().getString("page.404");
    public static final String ERROR_500 = RoutingUtil.getRoutingBundle().getString("page.500");

    private SharedPage() {

    }
}
