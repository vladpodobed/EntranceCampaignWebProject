package by.epam.training.task06.util.tag;

import by.epam.training.task06.entity.Certificate;
import by.epam.training.task06.entity.User;
import by.epam.training.task06.parameter.SharedParameter;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * <p>
 *     User tag
 *     Provides ability of building abiturient info and select abiturient form
 * </p>
 */
public class SelectAbiturientTag extends SimpleTagSupport {
    private User user;
    private String facultyName;
    private String commandOption;
    private String switchLangAction;

    @Override
    public void doTag() throws JspException {
        ResourceBundle bundle = getBundle();
        PageContext pageContext = (PageContext) getJspContext();
        JspWriter out = pageContext.getOut();

        try {
            out.println("<tr>");
            out.println("   <td>" + user.getName() + "</td>");
            out.println("   <td>" + user.getEmail() + "</td>");
            out.println("   <td>" + user.getSchoolCertificateScore() + "</td>");
            for (Certificate certificate : user.getCertificates()) {
                out.println(" <td>" + certificate.getResult() + "</td>\n");
            }
            out.println("   <td>");
            out.println("       <form action=\"/select_abiturient\" method=\"post\" class=\"navbar-form\">");
            out.println("           <input type=\"hidden\" name=\"command\" value=\"select_abiturient\"/>");
            out.println("             <input type=\"hidden\" name=\"command_option\" value=\"" + getCommandOption() + "\"/>");
            out.println("             <input type=\"hidden\" name=\"switch_lang_action\" value=\"" + getSwitchLangAction() + "\"/>");
            out.println("             <input type=\"hidden\" name=\"faculty_name\" value=\"" + getFacultyName() + "\"/>");
            out.println("             <input type=\"hidden\" name=\"candidate_id\" value=\"" + user.getId() + "\"/>");
            out.println("             <button type=\"submit\" class=\"btn btn-primary btn-xs btn-group-justified\">" + bundle.getString("admin.select_abiturient") + "</button>");
            out.println("       </form>");
            out.println("   </td>");
            out.println("</tr>");
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
    }

    private ResourceBundle getBundle() {
        PageContext context = (PageContext) getJspContext();
        HttpSession session = context.getSession();
        if (null != session) {
            String bundle = (String) session.getAttribute(SharedParameter.BUNDLE);
            if (bundle != null) {
                return ResourceBundle.getBundle(bundle);
            }
        }
        return ResourceBundle.getBundle("resources.i18n_en");
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getCommandOption() {
        return commandOption;
    }

    public void setCommandOption(String commandOption) {
        this.commandOption = commandOption;
    }

    public String getSwitchLangAction() {
        return switchLangAction;
    }

    public void setSwitchLangAction(String switchLangAction) {
        this.switchLangAction = switchLangAction;
    }
}
