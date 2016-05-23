package ua.nure.revuk.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.revuk.SummaryTask4.Path;
import ua.nure.revuk.SummaryTask4.db.DBManager;
import ua.nure.revuk.SummaryTask4.db.entity.User;
import ua.nure.revuk.SummaryTask4.exception.AppException;
import ua.nure.revuk.SummaryTask4.web.UtilsWeb;
import ua.nure.revuk.SummaryTask4.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Lists users.
 */
public class ListUsersCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ListUsersCommand.class);

    private final DBManager dbManager;

    public ListUsersCommand(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("List users commands starts");
        // the default path of transition in case of success
        String forward = Path.PAGE_LIST_USERS;

        // get this session
        HttpSession session = request.getSession();

        //delete "OkMessage" from session
        UtilsWeb.removeUnwantedAttributeOkMessageFromSession(session, request);
        LOG.trace("delete \"OkMessage\" from session");

        // get all users of BD
        List<User> allUsers = dbManager.getAllUsers();
        LOG.trace("Found in DB allUser: " + allUsers);

        // put user list to request
        session.setAttribute("allUsers", allUsers);
        LOG.trace("Set the request attribute: allUsers");

        LOG.debug("List Users commands finished");
        return forward;
    }
}