package ua.nure.revuk.SummaryTask4.web.command.common;

import org.apache.log4j.Logger;
import ua.nure.revuk.SummaryTask4.Path;
import ua.nure.revuk.SummaryTask4.db.DBManager;
import ua.nure.revuk.SummaryTask4.db.Role;
import ua.nure.revuk.SummaryTask4.db.entity.User;
import ua.nure.revuk.SummaryTask4.exception.AppException;
import ua.nure.revuk.SummaryTask4.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Login command.
 */
public class LoginCommand extends Command {

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    private final DBManager dbManager;

    public LoginCommand(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {

        LOG.debug("Command login starts");
        // get this session
        HttpSession session = request.getSession();

        // the default path of transition in case of success
        String forward = Path.COMMAND_PAGE_ERROR;

        // get parameter login
        String login = request.getParameter("login");
        LOG.trace("Request parameter login --> " + login);
        // get parameter password
        String password = request.getParameter("password");

        // check valid login/password
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            LOG.error("Login/password empty!");
            // set attribute errorMessage in session
            session.setAttribute("errorMessage", "Login/password cannot be empty");
            return "/";
        }

        // obtain login and password from a request
        User user = dbManager.getUserByLogin(login);
        LOG.trace("Found in DB user --> " + user);

        // check valid user/password
        if (user == null || !password.equals(user.getPassword())) {
            LOG.trace("Cannot find user with such login/password");
            // set attribute errorMessage in session
            session.setAttribute("errorMessage", "Cannot find user with such login/password");
            return "/";
        }

        // get user role
        Role userRole = Role.getRole(user);
        LOG.trace("user role --> " + userRole);

        // check valid role
        if ((userRole == Role.ADMIN) || (userRole == Role.DRIVER) || (userRole == Role.DISPATCHER)) {
            LOG.trace("Role correct");
            // the path of transition in case of success
            forward = Path.COMMAND_LIST_FLIGHTS;
        }

        // set attribute user in session
        session.setAttribute("user", user);
        LOG.trace("Set the session attribute: user --> " + user);

        // set attribute userRole in session
        session.setAttribute("userRole", userRole);
        LOG.trace("Set the session attribute: role --> " + userRole);
        LOG.info("User " + user + " logged as " + userRole.toString().toLowerCase());
        LOG.debug("Command login finished");

        return forward;
    }
}