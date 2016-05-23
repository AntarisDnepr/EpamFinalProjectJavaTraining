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
 * @since 01.03.2016
 */

/**
 * Flight user.
 */

public class DeleteFlightCommand extends Command {

    private static final Logger LOG = Logger.getLogger(DeleteFlightCommand.class);

    private final DBManager dbManager;

    public DeleteFlightCommand(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Delete flight command starts");
        // the default path of transition in case of success
        String forward = Path.COMMAND_LIST_FLIGHTS;

        // get this session
        HttpSession session = request.getSession();

        // the id flight to be removed
        String deleteFlightId = request.getParameter("deleteFlightId");
        LOG.debug("Delete flight by id: " + deleteFlightId);

        // validation checks parameters. If not - throw exception
        if (deleteFlightId != null && !deleteFlightId.equals("") && ValidUtils.isNumber(deleteFlightId)) {
            LOG.trace("parameter of id flight correct");

            // delete flight from id.
            // successfulExecutionUpdate - successful execution must be 1
            int successfulExecutionDelete = dbManager.deleteFlight(Integer.valueOf(deleteFlightId));

            LOG.trace("delete flight in DB: successful execution must be 1 --> " + successfulExecutionDelete);

            //  put "okMessage" to request
            session.setAttribute("okMessage", "okMessage");
            LOG.trace("Set the request attribute: okMessage --> " + "okMessage");

        } else {
            LOG.error("No parameter of id flight, or incorrect!");
            throw new AppException("Cannot delete flight! Incorrect id parameter.");
        }

        LOG.debug("Delete flight command finished");
        return forward;
    }
}