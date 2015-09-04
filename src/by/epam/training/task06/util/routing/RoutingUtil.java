package by.epam.training.task06.util.routing;

import java.util.ResourceBundle;

/**
 * <p>
 *     Provides resources using
 * </p>
 */
public final class RoutingUtil {
    private final static ResourceBundle routingBundle = ResourceBundle.getBundle("resources.routing");

    private RoutingUtil() {

    }

    public static ResourceBundle getRoutingBundle() {
        return routingBundle;
    }
}
