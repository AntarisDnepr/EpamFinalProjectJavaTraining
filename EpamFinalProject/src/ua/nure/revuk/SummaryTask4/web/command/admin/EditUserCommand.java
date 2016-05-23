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
 * Edit user
 */
public class EditUserCommand extends Command {

    private static final Logger LOG = Logger.getLogger(EditUserCommand.class);

    private final DBManager dbManager;

    public EditUserCommand(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Edit user commands starts");
        // the default path of transition in case of success
        String forward = Path.PAGE_EDIT_USER;
        // get this session
        HttpSession session = request.getSession();

        // the id of user to be edited
        String editUserId = request.getParameter("editUserId");
        LOG.debug("Edit user id: " + editUserId);

        // insert in the database flag
        String insertToBD = request.getParameter("insertToBD");

        //  get user of request
        User editUser = (User) session.getAttribute("editUser");
        LOG.trace("Get the request attribute: editUser --> " + editUser);

        // validation checks parameters to insert to BD
        if (editUser != null & insertToBD != null && insertToBD.equals("insertToBD")) {
            LOG.trace("Flag of insert to BD.");

            // parameter login. If the user does not enter anything, the previous value is set
            String login = request.getParameter("login");
            if (login == null || login.equals("")) {
                login = editUser.getLogin();
            }
            LOG.debug("parameter of login: " + login);

            // parameter password. If the user does not enter anything, the previous value is set
            String password = request.getParameter("password");
            if (password == null || password.equals("")) {
                password = editUser.getPassword();
            }

            // parameter firstName. If the user does not enter anything, the previous value is set
            String firstName = request.getParameter("firstName");
            if (firstName == null || firstName.equals("")) {
                firstName = editUser.getFirstName();
            }
            LOG.debug("parameter of firstName: " + firstName);

            // parameter lastName. If the user does not enter anything, the previous value is set
            String lastName = request.getParameter("lastName");
            if (lastName == null || lastName.equals("")) {
                lastName = editUser.getLastName();
            }
            LOG.debug("parameter of lastName: " + lastName);

            // parameter mail. If the user does not enter anything, the previous value is set
            String mail = request.getParameter("mail");
            if (mail == null || mail.equals("")) {
                mail = editUser.getMail();
            }
            LOG.debug("parameter of mail: " + mail);

            // parameter photoLink. If the user does not enter anything, the previous value is set
            String photoLink = request.getParameter("photoLink");
            if (photoLink == null || photoLink.equals("")) {
                photoLink = editUser.getPhotoLink();
            }
            LOG.debug("parameter of photoLink: " + photoLink);

            // parameter roleId. If the user does not enter anything, the previous value is set
            String roleId = request.getParameter("roleId");
            if (roleId == null || roleId.equals("")) {
                roleId = String.valueOf(editUser.getRoleId());
            }
            LOG.debug("parameter of roleId: " + roleId);

            // validation parameters of user checks parameters
            String validationParametersUser =
                    ValidUtils.validUserInput(login, password, firstName, lastName, mail, photoLink, roleId);

            if (validationParametersUser.equals("ok")) {
                LOG.debug("input parameters user form validation - ok");

                editUser.setLogin(login);
                editUser.setPassword(password);
                editUser.setFirstName(ValidUtils.nameInputFormat(firstName));
                editUser.setLastName(ValidUtils.nameInputFormat(lastName));
                if (mail != null && !mail.equals("")) {
                    editUser.setMail(mail);
                }
                editUser.setPhotoLink(photoLink);
                editUser.setRoleId(Integer.valueOf(roleId));
                LOG.debug("prepared user input in DB: " + editUser);

                // update user in BD
                // successfulExecutionUpdate - successful execution must be 1
                int successfulExecutionAdd = dbManager.updateUserById(editUser);
                LOG.debug("insert user in DB. Successful execution must be 1 --> " + successfulExecutionAdd);

                //  put "okMessage" to request
                session.setAttribute("okMessage", "okMessageUserEdit");
                LOG.trace("Set the request attribute: okMessage --> " + "okMessageUserEdit");

                // path of transition in case of success
                forward = Path.COMMAND_LIST_USERS;

            } else {
                LOG.error("input parameters of user form incorrect! --> " + validationParametersUser);
                throw new AppException("input parameters of user form incorrect! " + validationParametersUser);
            }

        } else if (ValidUtils.isNumber(editUserId)) {
            // get user by id
            editUser = dbManager.getUserById(Integer.valueOf(editUserId));
            LOG.trace("Found in DB: user --> " + editUser);

            //  put user to request
            session.setAttribute("editUser", editUser);
            LOG.trace("Set the request attribute: editUser --> " + editUser);

        } else {
            LOG.error("input parameters of user incorrect!");
            throw new AppException("input parameters of user incorrect!");
        }

        LOG.debug("Edit user commands finished");
        return forward;
    }
}