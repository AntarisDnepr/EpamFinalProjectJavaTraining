package ua.nure.revuk.SummaryTask4.web.command.dispatcher;

import org.apache.log4j.Logger;
import ua.nure.revuk.SummaryTask4.Path;
import ua.nure.revuk.SummaryTask4.db.DBManager;
import ua.nure.revuk.SummaryTask4.db.Role;
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
 * Delete Request.
 */

public class DeleteRequestCommand extends Command {

    private static final Logger LOG = Logger.getLogger(DeleteRequestCommand.class);

    private final DBManager dbManager;

    public DeleteRequestCommand(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Delete requests command starts");
        // the default path of transition in case of success
        String forward = Path.COMMAND_LIST_REQUEST;

        // get this session
        HttpSession session = request.getSession();

        // the id request to be removed
        String deleteRequestId = request.getParameter("deleteRequestId");

        // the id flight to be removed in request
        String flightId = request.getParameter("flightId");

        // get user from session
        User user = (User) session.getAttribute("user");
        LOG.debug("Get Attribute user: " + user);

        //check valid user
        if (user == null) {
            LOG.error("User do not have permission to access the requested resource");
            throw new AppException("You do not have permission to access the requested resource.");
        }

        // validation checks parameters. If not - throw exception
        if (deleteRequestId != null && !deleteRequestId.equals("") && ValidUtils.isNumber(deleteRequestId)) {
            if ((Role.getRole(user) == Role.DRIVER) && flightId != null && ValidUtils.isNumber(flightId)) {

                // delete request from id.
                // successfulExecutionUpdate - successful execution must be 1
                int successfulExecutionDelete = dbManager.deleteRequestByDriver(
                        Integer.valueOf(deleteRequestId), user.getId(), Integer.valueOf(flightId));
                LOG.trace("delete Request in DB: successful execution must be 1 --> " + successfulExecutionDelete);

                //  put "okMessage" to request
                setAttributeOkMessageRequestCanceled(session);

                // the default path of transition in case of success
                forward = Path.COMMAND_DRIVER_PERSONAL;

            } else if ((Role.getRole(user) == Role.ADMIN) || (Role.getRole(user) == Role.DISPATCHER)) {

                // delete request from id.
                // successfulExecutionUpdate - successful execution must be 1
                int successfulExecutionDelete = dbManager.deleteRequest(Integer.valueOf(deleteRequestId));
                LOG.trace("delete Request in DB: successful execution must be 1 --> " + successfulExecutionDelete);

                //  put "okMessage" to request
                setAttributeOkMessageRequestCanceled(session);
            }
        } else {
            LOG.error("Cannot delete request!");
            throw new AppException("Cannot delete request!");
        }

        LOG.debug("Delete requests command finished");
        return forward;
    }

    private void setAttributeOkMessageRequestCanceled(HttpSession session) {
        //  put "okMessage" to request
        session.setAttribute("okMessage", "okMessageRequestCanceled");
        LOG.trace("Set the request attribute: okMessage --> " + "okMessageRequestCanceled");
    }
}