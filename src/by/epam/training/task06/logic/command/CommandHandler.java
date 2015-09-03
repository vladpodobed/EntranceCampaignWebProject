package by.epam.training.task06.logic.command;

import by.epam.training.task06.logic.command.impl.NoSuchCommand;
import by.epam.training.task06.logic.command.impl.admin.AdminFinishEntranceCampaign;
import by.epam.training.task06.logic.command.impl.admin.AdminSelectAbiturient;
import by.epam.training.task06.logic.command.impl.page.*;
import by.epam.training.task06.logic.command.impl.user.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *     Allows commands handling by getting command parameter and returning command instance
 * </p>
 */
public class CommandHandler {
    public final static String SWITCH_LANG = "switch_lang";
    public final static String SHOW_HELP = "show_help";
    public final static String SHOW_UNIVERSITY_STRUCTURE = "show_university_structure";
    public final static String LOG_IN = "log_in";
    public final static String LOG_OUT = "log_out";
    public final static String SHOW_SIGN_UP_FORM = "show_sign_up_form";
    public final static String SIGN_UP = "sign_up";
    public final static String SHOW_SELECT_FACULTY = "show_select_faculty";
    public final static String SELECT_FACULTY = "select_faculty";
    public final static String SELECT_ABITURIENT = "select_abiturient";
    public final static String SHOW_PROFILE = "show_profile";
    public final static String SHOW_UPDATE_PROFILE = "show_update_profile";
    public final static String TAKE_DOCUMENTS = "take_documents";
    public final static String REGISTER_AT_FACULTY = "register_at_faculty";
    public final static String UPDATE_PROFILE = "update_profile";
    public final static String FINISH_ENTRANCE_CAMPAIGN = "finish_entrance_campaign";
    public final static String SHOW_INDEX_PAGE = "show_index_page";

    private static final CommandHandler instance = new CommandHandler();
    private Map<String, Command> commandsMap = new HashMap<>();

    private CommandHandler() {
        commandsMap.put(SWITCH_LANG, new SwitchLang());
        commandsMap.put(SHOW_SIGN_UP_FORM, new ShowSignUpForm());
        commandsMap.put(SIGN_UP, new SignUpUser());
        commandsMap.put(LOG_IN, new LogInUser());
        commandsMap.put(LOG_OUT, new LogOutUser());
        commandsMap.put(SHOW_UNIVERSITY_STRUCTURE, new ShowUniversityStructure());
        commandsMap.put(SHOW_HELP, new ShowHelp());
        commandsMap.put(REGISTER_AT_FACULTY, new RegisterAtFaculty());
        commandsMap.put(SELECT_ABITURIENT, new AdminSelectAbiturient());
        commandsMap.put(FINISH_ENTRANCE_CAMPAIGN, new AdminFinishEntranceCampaign());
        commandsMap.put(SHOW_PROFILE, new ShowProfile());
        commandsMap.put(UPDATE_PROFILE, new UpdateProfile());
        commandsMap.put(TAKE_DOCUMENTS, new TakeDocuments());
        commandsMap.put(SHOW_UPDATE_PROFILE, new ShowUpdateProfileForm());
        commandsMap.put(SHOW_SELECT_FACULTY, new ShowSelectFaculty());
        commandsMap.put(SELECT_FACULTY, new SelectFaculty());
        commandsMap.put(SHOW_INDEX_PAGE, new ShowIndex());
    }

    public static CommandHandler getInstance() {
        return instance;
    }

    public Command getCommand(String commandName) {
        Command command = commandsMap.get(commandName);
        if (null == command) {
            command = NoSuchCommand.getInstance();
        }
        return command;
    }
}
