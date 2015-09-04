package by.epam.training.task06.logic.help;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *     Created for ability of using same pages for different needs. Allows handling commands option getting command option and returning suitable form action
 * </p>
 */
public final class CommandOptionToActionMapping {
    private static final CommandOptionToActionMapping instance = new CommandOptionToActionMapping();
    private Map<String, String> commandOptionToAction;

    public static final String ADMIN_FACULTY_STATISTICS = "admin_show_faculty_statistics";
    public static final String ADMIN_SELECT_ABITURIENT = "admin_select_abiturient";
    public static final String USER_REGISTER_AT_FACULTY = "user_register_at_faculty";

    private CommandOptionToActionMapping() {
        commandOptionToAction = new HashMap<>();
        commandOptionToAction.put(ADMIN_FACULTY_STATISTICS, UrlToCommandMapping.FACULTY_STATISTICS);
        commandOptionToAction.put(ADMIN_SELECT_ABITURIENT, UrlToCommandMapping.SELECT_ABITURIENT);
        commandOptionToAction.put(USER_REGISTER_AT_FACULTY, UrlToCommandMapping.REGISTER_AT_FACULTY);
    }

    public static CommandOptionToActionMapping getInstance(){
        return instance;
    }

    public String getAction(String commandOption){
        String action = commandOptionToAction.get(commandOption);
        if(null == action){
            return UrlToCommandMapping.INDEX;
        }
        return action;
    }
}
