package ua.nure.revuk.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.revuk.SummaryTask4.Path;
import ua.nure.revuk.SummaryTask4.db.DBManager;
import ua.nure.revuk.SummaryTask4.db.entity.User;
import ua.nure.revuk.SummaryTask4.exception.AppException;
import ua.nure.revuk.SummaryTask4.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Edit user
 */
public class FindUserCommand extends Command {

    private static final Logger LOG = Logger.getLogger(FindUserCommand.class);

    private final DBManager dbManager;

    public FindUserCommand(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Edit user commands starts");
        // the default path of transition in case of success
        String forward = Path.PAGE_FIND_USER;

        // get this session
        HttpSession session = request.getSession();

        // get flag "find" in the database
        String findUsers = request.getParameter("findUsers");
        LOG.trace("Get parameter findUsers:" + findUsers);

        // get search parameter
        String searchParameter = request.getParameter("searchParameter");
        LOG.trace("Get parameter searchParameter:" + searchParameter);


        // validation checks parameters to find Users in BD
        if (findUsers != null && findUsers.equals("findUsers") &&
                searchParameter != null && searchParameter.length() < 36) {
            LOG.trace("Flag of find users to BD.");

            // get all users of BD
            List<User> allUsers = dbManager.getUsersByParameters("%" + searchParameter.toLowerCase() + "%");
            LOG.trace("Found in DB allUser: " + allUsers);

            // put user list to request
            session.setAttribute("allUsers", allUsers);
            LOG.trace("Set the request attribute: allUsers");

            //  put "okMessage" to request
            session.setAttribute("okMessage", "okMessageFindUser");
            LOG.trace("Set the request attribute: okMessage --> " + "okMessageFindUser");

            // the default path of transition in case of success
            forward = Path.PAGE_LIST_USERS;
        }

        LOG.debug("Edit user commands finished");
        return forward;
    }
}