package ua.nure.revuk.SummaryTask4.web.command.dispatcher;

import org.apache.log4j.Logger;
import ua.nure.revuk.SummaryTask4.Path;
import ua.nure.revuk.SummaryTask4.db.DBManager;
import ua.nure.revuk.SummaryTask4.db.entity.Flight;
import ua.nure.revuk.SummaryTask4.db.entity.User;
import ua.nure.revuk.SummaryTask4.exception.AppException;
import ua.nure.revuk.SummaryTask4.web.UtilsWeb;
import ua.nure.revuk.SummaryTask4.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import static ua.nure.revuk.SummaryTask4.db.util.ValidUtils.inputValidFlightCreate;
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
 * Flight create
 */
public class FlightCreateCommand extends Command {

    private static final Logger LOG = Logger.getLogger(FlightCreateCommand.class);

    private final DBManager dbManager;

    public FlightCreateCommand(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Flight create command starts");
        // the default path of transition in case of success
        String forward = Path.PAGE_FLIGHT_CREATE;

        // get this session
        HttpSession session = request.getSession();

        // get user from session
        User user = (User) session.getAttribute("user");
        LOG.debug("Get Attribute user: " + user);

        //check valid user
        if (user == null) {
            LOG.error("User do not have permission to access the requested resource");
            throw new AppException("You do not have permission to access the requested resource.");
        }

        // insert in the database flag
        String insertToBD = request.getParameter("insertToBD");

        // validation checks parameters to insert to BD
        if (insertToBD != null && insertToBD.equals("insertToBD")) {
            LOG.trace("Flag of insert to BD.");

            // get parameter flightNumber
            String flightNumber = request.getParameter("flightNumber");
            LOG.debug("parameter of flightNumber: " + flightNumber);
            // get parameter dateDeparture
            String dateDeparture = request.getParameter("dateDeparture");
            LOG.debug("parameter of dateDeparture: " + dateDeparture);
            // get parameter destination
            String destination = request.getParameter("destination");
            LOG.debug("parameter of destination: " + destination);
            // get parameter distance
            String distance = request.getParameter("distance");
            LOG.debug("parameter of distance: " + distance);

            String validationParametersFlight =
                    inputValidFlightCreate(flightNumber, dateDeparture, destination, distance);

            // validation checks parameters flight. If not - throw exception
            if (validationParametersFlight.equals("ok")) {
                LOG.debug("input parameters flight form validation - ok");

                Flight flight = new Flight();
                // get this date
                Date sqlStartDate = new Date(new java.util.Date().getTime());

                // set flightNumber
                flight.setFlightNumber(Integer.parseInt(flightNumber));
                // set date creation
                flight.setDateCreation(sqlStartDate);
                // set date departure, if the user enters values
                if (dateDeparture != null && !dateDeparture.equals("")) {
                    flight.setDateDeparture(Date.valueOf(dateDeparture));
                }
                // set destination, if the user enters values
                if (destination != null && !destination.equals("")) {
                    flight.setDestination(destination);
                }
                // set distance, if the user enters values
                if (distance != null && !distance.equals("")) {
                    flight.setDistance(Double.valueOf(distance));
                }

                LOG.debug("prepared flight input in DB: " + flight);

                // create flight.
                // successfulExecutionUpdate - successful execution must be 1
                int successfulExecutionAdd = dbManager.insertFlight(flight, user.getId());

                LOG.debug("insert flight in DB. Successful execution must be 1 --> " + successfulExecutionAdd);

                // put "okMessage" to request
                session.setAttribute("okMessage", "okMessageFlightCreate");
                LOG.trace("Set the request attribute: okMessage --> " + "okMessageFlightCreate");

                // get parameter sendMail
                String sendMail = request.getParameter("sendMail");
                LOG.debug("parameter of sendMail: " + sendMail);

                //if sendMail true - send to drivers
                if (sendMail != null && isBoolean(sendMail) && Boolean.valueOf(sendMail)) {

                    // get all emails of DRIVER in BD
                    // put emails to List
                    List<String> emails = dbManager.getAllEmails();
                    LOG.trace("Found emails of DRIVER in BD " + emails);


                    // create new message by drivers
                    StringBuilder message = new StringBuilder();

                    // put title in message
                    message.append("Hello! In system a new flight. \n\nInfo about flight:");
                    // put flight in message
                    message.append("\nflight number: ");
                    message.append(flight.getFlightNumber());
                    // put date creation in message
                    message.append("\ndate creation: ");
                    message.append(flight.getDateCreation());
                    // put date departure in message
                    message.append("\ndate departure: ");
                    message.append(flight.getDateDeparture());
                    // put destination in message
                    message.append("\ndestination: ");
                    message.append(flight.getDestination());
                    // put distance in message
                    message.append("\ndistance: ");
                    message.append(flight.getDistance());
                    message.append("\nХорошего дня!");

                    LOG.debug("create new message by drivers " + message);

                    //send new flight to drivers e-mail
                    UtilsWeb.sendMail(message.toString(), UtilsWeb.SUBJECT_NEW_FLIGHT, emails);

                }

                // path of transition in case of success
                forward = Path.COMMAND_LIST_FLIGHTS;

            } else {
                LOG.error("input parameters of flight form incorrect! --> " + validationParametersFlight);
                throw new AppException("input parameters of flight form incorrect! " + validationParametersFlight);
            }
        }

        LOG.debug("Flight create command finished");
        return forward;
    }
}