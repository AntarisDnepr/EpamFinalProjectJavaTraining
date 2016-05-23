package ua.nure.revuk.SummaryTask4.web.command.dispatcher;

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
 * Update flight status
 */

public class ChangeFlightStatusCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ChangeFlightStatusCommand.class);

    private final DBManager dbManager;

    public ChangeFlightStatusCommand(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Update flight status command starts");
        // the default path of transition in case of success
        String forward = Path.COMMAND_LIST_FLIGHTS;

        // get this session
        HttpSession session = request.getSession();
        // change flight status in the database flag

        String changeStatusFlightId = request.getParameter("changeStatusFlightId");

        // validation checks parameters to change flight status to BD. If not - throw exception
        if (changeStatusFlightId != null && !changeStatusFlightId.equals("") &&
                ValidUtils.isNumber(changeStatusFlightId)) {

            // change status flight id (id = 4)
            // successfulExecutionUpdate - successful execution must be 1
            int successfulExecution = dbManager.updateFlightStatus(Integer.valueOf(changeStatusFlightId), 4);
            LOG.trace("Update flight status in DB: successful execution must be 1 --> " + successfulExecution);

            // put "okMessage" to request
            session.setAttribute("okMessage", "okMessageFlightCanceled");
            LOG.trace("Set the request attribute: okMessage --> " + "okMessageFlightCanceled");

        } else {
            LOG.error("Cannot change status!");
            throw new AppException("Cannot change status!");
        }

        LOG.debug("Update flight status command finished");
        return forward;
    }
}