package ua.nure.revuk.SummaryTask4.web.command.driver;

import org.apache.log4j.Logger;
import ua.nure.revuk.SummaryTask4.Path;
import ua.nure.revuk.SummaryTask4.db.DBManager;
import ua.nure.revuk.SummaryTask4.db.entity.Flight;
import ua.nure.revuk.SummaryTask4.db.entity.Request;
import ua.nure.revuk.SummaryTask4.db.entity.User;
import ua.nure.revuk.SummaryTask4.db.util.ValidUtils;
import ua.nure.revuk.SummaryTask4.exception.AppException;
import ua.nure.revuk.SummaryTask4.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.revuk.SummaryTask4.db.util.ValidUtils.inputValidCharacteristicTruck;
import static ua.nure.revuk.SummaryTask4.db.util.ValidUtils.isBoolean;

/**
 * Epam Final Project
 * Revuk Group Java 2-1
 *
 * @author Revuk Oleksandr
 * @version 1.0
 * @since 05.02.2016
 */

/**
 * Request create
 */
public class RequestCreateCommand extends Command {

    private static final Logger LOG = Logger.getLogger(RequestCreateCommand.class);

    private final DBManager dbManager;

    public RequestCreateCommand(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Request create command starts");
        // the default path of transition in case of success
        String forward = Path.PAGE_REQUEST_CREATE;

        // get this session
        HttpSession session = request.getSession();

        // insert in the database flag
        String insertToBD = request.getParameter("insertToBD");

        // validation checks parameters to insert to BD
        if (insertToBD != null && insertToBD.equals("insertToBD")) {
            LOG.trace("Flag of insert to BD.");

            // get attribute user
            User user = (User) session.getAttribute("user");
            LOG.trace("get attribute user: " + user);
            // get attribute flightId
            String flightId = (String) session.getAttribute("flightId");
            LOG.trace("get attribute flightId: " + flightId);
            // get parameter carrying
            String carrying = request.getParameter("carrying");
            LOG.trace("parameter of carrying: " + carrying);
            // get parameter capacity
            String capacity = request.getParameter("capacity");
            LOG.trace("parameter of capacity: " + capacity);
            // get parameter length
            String length = request.getParameter("length");
            LOG.trace("parameter of length: " + length);
            // get parameter lorryWithSides
            String lorryWithSides = request.getParameter("lorryWithSides");
            LOG.trace("parameter of lorryWithSides: " + lorryWithSides);
            // get parameter refrigerator
            String refrigerator = request.getParameter("refrigerator");
            LOG.trace("parameter of refrigerator: " + refrigerator);

            String validationCharacteristicTruck = inputValidCharacteristicTruck(carrying, capacity, length);

            // validation checks parameters user and flightId. If not - throw exception
            if (user != null && ValidUtils.isNumber(flightId) && validationCharacteristicTruck.equals("ok") &&
                    (isBoolean(lorryWithSides, refrigerator, "true"))) {
                LOG.debug("input parameters user form validation - ok");

                Request requestInBD = new Request();

                // set flight Id
                requestInBD.setFlightId(Integer.valueOf(flightId));
                // set carrying, if the user enters values
                if (carrying != null && !carrying.equals("")) {
                    requestInBD.setCarrying(Double.valueOf(carrying));
                }
                // set capacity, if the user enters values
                if (capacity != null && !capacity.equals("")) {
                    requestInBD.setCapacity(Double.valueOf(capacity));
                }
                // set length, if the user enters values
                if (length != null && !length.equals("")) {
                    requestInBD.setLength(Double.valueOf(length));
                }
                // set lorryWithSides
                requestInBD.setLorryWithSides(Boolean.valueOf(lorryWithSides));
                // set refrigerator
                requestInBD.setRefrigerator(Boolean.valueOf(refrigerator));
                // set user id
                requestInBD.setDriverId(user.getId());

                LOG.debug("prepared request input in DB: " + requestInBD);

                // insert "request" in the BD
                // successfulExecutionUpdate - successful execution must be 1
                int successfulExecutionAdd = dbManager.insertRequest(requestInBD);
                LOG.debug("insert request in DB. Successful execution must be 1 --> " + successfulExecutionAdd);

                // put "okMessage" to request
                session.setAttribute("okMessage", "okMessageRequestCreate");
                LOG.trace("Set the request attribute: okMessage --> " + "okMessageRequestCreate");

                // path of transition in case of success
                forward = Path.COMMAND_LIST_FLIGHTS;

            } else {
                LOG.error("parameters user/flightId or characteristic truck incorrect! Characteristic - "
                        + validationCharacteristicTruck);
                throw new AppException("parameters user/flightId or characteristic truck incorrect! Characteristic - "
                        + validationCharacteristicTruck);
            }
        } else {
            // get Attribute allFlights.
            List<Flight> allFlights = (ArrayList<Flight>) session.getAttribute("allFlights");
            LOG.trace("get attribute allFlights: " + allFlights);

            // get parameter idFlight
            String idFlight = request.getParameter("idFlight");
            LOG.trace("get attribute idFlight: " + idFlight);

            //  validation checks parameters allFlights and idFlight. They  should not be NULL
            if (allFlights != null && idFlight != null) {

                LOG.trace("validation checks parameters allFlights and idFlight: not NULL ");

                // find flight for id of all flights
                for (Flight flight : allFlights) {
                    if (flight.getId() == Integer.valueOf(idFlight)) {

                        // set flight in attribute "requestFlight".
                        session.setAttribute("requestFlight", flight);
                        LOG.trace("set attribute requestFlight: " + flight);

                        // set id from flight in attribute "requestFlight".
                        session.setAttribute("flightId", idFlight);
                        LOG.trace("set attribute flightId: " + idFlight);

                        break;
                    }
                }
            }
        }

        LOG.debug("Request create command finished");
        return forward;
    }
}