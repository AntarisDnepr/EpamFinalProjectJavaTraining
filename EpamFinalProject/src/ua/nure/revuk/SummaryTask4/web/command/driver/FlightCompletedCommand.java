package ua.nure.revuk.SummaryTask4.web.command.driver;

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
 * Flight completed
 */
public class FlightCompletedCommand extends Command {

    private static final Logger LOG = Logger.getLogger(FlightCompletedCommand.class);

    private final DBManager dbManager;

    public FlightCompletedCommand(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Flight completed command starts");
        // the default path of transition
        String forward = Path.PAGE_ERROR;

        // get this session
        HttpSession session = request.getSession();

        // insert in the database flag
        String insertToBD = request.getParameter("insertToBD");

        // validation checks parameters to insert to BD
        if (insertToBD != null && insertToBD.equals("insertToBD")) {
            LOG.trace("Flag of insert to BD.");

            // get parameter flightId
            String flightId = request.getParameter("flightId");
            LOG.debug("parameter of flightId: " + flightId);
            // get parameter truckId
            String truckId = request.getParameter("truckId");
            LOG.debug("parameter of truckId: " + truckId);
            // get parameter isTruckServiceable
            String isTruckServiceable = request.getParameter("isTruckServiceable");
            LOG.debug("parameter of isTruckServiceable: " + isTruckServiceable);

            // validation checks parameters approveRequestTruckId - is a number. If not - throw exception
            if (ValidUtils.isNumber(flightId) && ValidUtils.isNumber(truckId) &&
                    ValidUtils.isBoolean(isTruckServiceable)) {

                LOG.trace("input parameters correct!");

                // update flight (Completed).From flight id, truck id. Set truck serviceable.
                // successfulExecutionUpdate - successful execution must be 1
                int successfulExecution = dbManager.updateFlightCompleted(
                        Integer.valueOf(flightId), Integer.valueOf(truckId), Boolean.valueOf(isTruckServiceable));
                LOG.trace("Update flight completed in DB: successful execution must be 1 --> " + successfulExecution);

                // put "okMessage" to request
                session.setAttribute("okMessage", "okMessageFlightCompleted");
                LOG.trace("Set the request attribute: okMessage --> " + "okMessageFlightCompleted");

                forward = Path.COMMAND_DRIVER_PERSONAL;

            } else {
                LOG.error("input parameters incorrect!");
                throw new AppException("input parameters incorrect!");
            }
        }

        LOG.debug("Flight completed Command finished");
        return forward;
    }
}