package ua.nure.revuk.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.revuk.SummaryTask4.Path;
import ua.nure.revuk.SummaryTask4.db.DBManager;
import ua.nure.revuk.SummaryTask4.db.entity.User;
import ua.nure.revuk.SummaryTask4.db.util.ValidUtils;
import ua.nure.revuk.SummaryTask4.exception.AppException;
import ua.nure.revuk.SummaryTask4.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Epam Final Project
 * Revuk Group Java 2-1
 *
 * @author Revuk Oleksandr
 * @version 1.0
 * @since 05.02.2016
 */

/**
 * Registration user.
 */
public class RegistrationUserCommand extends Command {

    private static final Logger LOG = Logger.getLogger(RegistrationUserCommand.class);

    private final DBManager dbManager;

    public RegistrationUserCommand(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Registration user command starts");
        // the default path of transition in case of success
        String forward = Path.PAGE_REGISTRATION_USER;

        // get this session
        HttpSession session = request.getSession();

        // insert in the database flag
        String insertToBD = request.getParameter("insertToBD");


        // validation checks parameters to insert to BD
        if (insertToBD != null && insertToBD.equals("insertToBD")) {
            LOG.trace("Flag of insert to BD.");

            // parameter login.
            String login = request.getParameter("login");
            LOG.debug("parameter of login: " + login);
            // parameter password.
            String password = request.getParameter("password");
            // parameter firstName.
            String firstName = request.getParameter("firstName");
            LOG.debug("parameter of firstName: " + firstName);
            // parameter lastName.
            String lastName = request.getParameter("lastName");
            LOG.debug("parameter of lastName: " + lastName);
            // parameter mail.
            String mail = request.getParameter("mail");
            LOG.debug("parameter of mail: " + mail);
            // parameter photoLink.
            String photoLink = request.getParameter("photoLink");
            LOG.debug("parameter of photoLink: " + photoLink);
            // parameter roleId.
            String roleId = request.getParameter("roleId");
            LOG.debug("parameter of roleId: " + roleId);

            // if parameter of photoLink empty - set default value
            if (photoLink == null || photoLink.equals("")) {
                LOG.debug("parameter of photoLink empty. Set default value");
                photoLink = "user.jpg";
            }


            // validation checks parameters
            String validationParametersUser =
                    ValidUtils.validUserInput(login, password, firstName, lastName, mail, photoLink, roleId);

            if (validationParametersUser.equals("ok")) {
                LOG.debug("input parameters user form validation - ok");

                User user = new User();

                user.setLogin(login);
                user.setPassword(password);
                user.setFirstName(ValidUtils.nameInputFormat(firstName));
                user.setLastName(ValidUtils.nameInputFormat(lastName));
                if (mail != null && !mail.equals("")) {
                    user.setMail(mail);
                }
                user.setPhotoLink(photoLink);
                user.setRoleId(Integer.valueOf(roleId));
                LOG.debug("prepared user input in DB: " + user);

                // insert "user" in the BD
                // successfulExecutionUpdate - successful execution must be 1
                int successfulExecutionAdd = dbManager.insertUser(user);
                LOG.debug("insert user in DB. Successful execution must be 1 --> " + successfulExecutionAdd);

                // put "okMessage" to request
                session.setAttribute("okMessage", "okMessageUserRegistration");
                LOG.trace("Set the request attribute: okMessage --> " + "okMessageUserRegistration");

                // path of transition in case of success
                forward = Path.COMMAND_LIST_USERS;
            } else {
                LOG.error("input parameters of user form incorrect! --> " + validationParametersUser);
                throw new AppException("input parameters of user form incorrect! " + validationParametersUser);
            }
        }

        LOG.debug("Registration user command finished");
        return forward;
    }
}