package ua.nure.revuk.SummaryTask4.web.command.common;

import org.apache.log4j.Logger;
import ua.nure.revuk.SummaryTask4.Path;
import ua.nure.revuk.SummaryTask4.db.DBManager;
import ua.nure.revuk.SummaryTask4.db.Role;
import ua.nure.revuk.SummaryTask4.db.bean.FlightUserTruckBean;
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
import java.util.List;

/**
 * Lists flights.
 */
public class ListFlightsCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ListFlightsCommand.class);

    private final DBManager dbManager;

    public ListFlightsCommand(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    /**
     * Serializable comparator used with TreeMap container. When the servlet
     * container tries to serialize the session it may fail because the session
     * can contain TreeMap object with not serializable comparator.
     */

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Commands List flights starts");
        // the default path of transition in case of success
        String forward = Path.PAGE_LIST_FLIGHTS;

        // get this session
        HttpSession session = request.getSession();

        //delete "OkMessage" from session
        UtilsWeb.removeUnwantedAttributeOkMessageFromSession(session, request);
        LOG.trace("delete \"OkMessage\" from session");

        //get user from session
        User user = (User) session.getAttribute("user");
        LOG.trace("Get Attribute user --> " + user);

        //check valid user
        if (user == null) {
            LOG.error("User do not have permission to access the requested resource");
            throw new AppException("You do not have permission to access the requested resource.");
        }

        if (Role.getRole(user) == Role.DRIVER) {

            LOG.debug("Role: " + Role.getRole(user));

            // the default path of transition in case of success (driver)
            forward = Path.PAGE_LIST_FLIGHTS_DRIVER;

            List<Flight> allFlights = dbManager.getAllFlightsFromDriver(user.getId());
            LOG.trace("Found flights from DRIVER, status OPEN: " + allFlights);

            // put allFlights list to request
            session.setAttribute("allFlights", allFlights);
            LOG.trace("Set the request attribute: all Flights");

        } else if ((Role.getRole(user) == Role.ADMIN) || (Role.getRole(user) == Role.DISPATCHER)) {

            LOG.debug("Role: " + Role.getRole(user));

            // Found flights from DISPATCHER or ADMIN
            List<FlightUserTruckBean> allFlights = dbManager.getAllFlight();
            LOG.trace("Found flights in DB from " + Role.getRole(user) + ": FlightUserTruckBean --> " + allFlights);

            // put allFlights list to request
            session.setAttribute("allFlights", allFlights);
            LOG.trace("Set the request attribute: all Flights");

            // get parameter approve
            String approve = request.getParameter("approve");
            LOG.trace("Request parameter approve: " + approve);

            if(approve!= null && approve.equals("approve")){
                // the default path of transition in case of success
                forward = Path.PAGE_LIST_FLIGHTS_APPROVE;
                LOG.trace("forward approve list flights");
            }

        } else {

            LOG.error("User do not have permission to access the requested resource");
            throw new AppException("You do not have permission to access the requested resource.");
        }

        LOG.debug("Commands list flights finished");
        return forward;
    }
}