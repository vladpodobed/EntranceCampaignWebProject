package by.epam.training.task06.logic.help;

import by.epam.training.task06.logic.command.Command;
import by.epam.training.task06.logic.command.impl.NoSuchCommand;
import by.epam.training.task06.logic.command.impl.page.*;
import by.epam.training.task06.logic.command.impl.user.SelectFaculty;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *     Created for commands reexecution when switching language according to form action
 * </p>
 */
public class UrlToCommandMapping {
    private static final UrlToCommandMapping instance = new UrlToCommandMapping();
    private Map<String, Command> urlToCommand;

    public final static String EMPTY = "/";
    public final static String INDEX = "/index";
    public final static String SIGN_UP_FORM = "/sign_up_form";
    public final static String REGISTER_AT_FACULTY = "/register_at_faculty";
    public final static String UNIVERSITY_STRUCTURE = "/university_structure";
    public final static String USER_PROFILE = "/profile";
    public final static String HELP = "/help";
    public final static String UPDATE_PROFILE = "/update_profile";
    public final static String SELECT_FACULTY = "/select_faculty";
    public final static String FACULTY_STATISTICS = "/faculty_statistics";
    public final static String SELECT_ABITURIENT = "/select_abiturient";

    private UrlToCommandMapping() {
        urlToCommand = new HashMap<>();
        urlToCommand.put(EMPTY, new ShowIndex());
        urlToCommand.put(INDEX, new ShowIndex());
        urlToCommand.put(SIGN_UP_FORM, new ShowSignUpForm());
        urlToCommand.put(UNIVERSITY_STRUCTURE, new ShowUniversityStructure());
        urlToCommand.put(HELP, new ShowHelp());
        urlToCommand.put(USER_PROFILE, new ShowProfile());
        urlToCommand.put(UPDATE_PROFILE, new ShowUpdateProfileForm());
        urlToCommand.put(SELECT_FACULTY, new ShowSelectFaculty());
        urlToCommand.put(FACULTY_STATISTICS, new SelectFaculty());
        urlToCommand.put(SELECT_ABITURIENT, new SelectFaculty());
        urlToCommand.put(REGISTER_AT_FACULTY, new SelectFaculty());
    }

    public static UrlToCommandMapping getInstance(){
        return instance;
    }

    public Command getCommand(String url){
        Command command = urlToCommand.get(url);
        if(null == command){
            return NoSuchCommand.getInstance();
        }
        return command;
    }
}
