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
 * Delete truck.
 */

public class DeleteTruckCommand extends Command {

    private static final Logger LOG = Logger.getLogger(DeleteTruckCommand.class);

    private final DBManager dbManager;

    public DeleteTruckCommand(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Delete truck command starts");
        // the default path of transition in case of success
        String forward = Path.COMMAND_LIST_TRUCKS;

        // get this session
        HttpSession session = request.getSession();

        // the id truck to be removed
        String deleteTruckId = request.getParameter("deleteTruckId");
        LOG.debug("Delete truck by id: " + deleteTruckId);

        // validation checks parameters
        if (deleteTruckId != null && !deleteTruckId.equals("") && ValidUtils.isNumber(deleteTruckId)) {
            LOG.trace("parameter of id truck correct");

            // delete truck from id.
            // successfulExecutionUpdate - successful execution must be 1
            int successfulExecutionDelete = dbManager.deleteTruck(Integer.valueOf(deleteTruckId));

            LOG.trace("delete truck in DB: successful execution must be 1 --> " + successfulExecutionDelete);

            //  put "okMessage" to request
            session.setAttribute("okMessage", "okMessageTruckDelete");
            LOG.trace("Set the request attribute: okMessage --> " + "okMessageTruckDelete");

        } else {
            LOG.error("No parameter of truck, or incorrect!");
            throw new AppException("Cannot delete truck! Incorrect parameter.");
        }

        LOG.debug("Delete truck command finished");
        return forward;
    }
}