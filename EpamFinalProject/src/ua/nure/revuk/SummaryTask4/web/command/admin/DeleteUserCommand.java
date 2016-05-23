package ua.nure.revuk.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.revuk.SummaryTask4.Path;
import ua.nure.revuk.SummaryTask4.db.DBManager;
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
 * Delete user.
 */

public class DeleteUserCommand extends Command {

    private static final Logger LOG = Logger.getLogger(DeleteUserCommand.class);

    private final DBManager dbManager;

    public DeleteUserCommand(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Delete user command starts");
        // the default path of transition in case of success
        String forward = Path.COMMAND_LIST_USERS;

        // get this session
        HttpSession session = request.getSession();

        // the id user to be removed
        String deleteUserId = request.getParameter("deleteUserId");
        LOG.debug("Delete user by id: " + deleteUserId);

        // validation checks parameters. If not - throw exception
        if (deleteUserId != null && !deleteUserId.equals("") && ValidUtils.isNumber(deleteUserId)) {
            LOG.trace("parameter of id user correct");

            // delete user from id.
            // successfulExecutionUpdate - successful execution must be 1
            int successfulExecutionDelete = dbManager.deleteUser(Integer.valueOf(deleteUserId));

            LOG.trace("delete user in DB: successful execution must be 1 --> " + successfulExecutionDelete);

            //  put "okMessage" to request
            session.setAttribute("okMessage", "okMessageUserDelete");
            LOG.trace("Set the request attribute: okMessage --> " + "okMessageUserDelete");

        } else {
            LOG.error("No parameter of truck, or incorrect!");
            throw new AppException("Cannot delete user! Incorrect parameter.");
        }

        LOG.debug("Delete user command finished");
        return forward;
    }
}