package ua.nure.revuk.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.revuk.SummaryTask4.Path;
import ua.nure.revuk.SummaryTask4.db.DBManager;
import ua.nure.revuk.SummaryTask4.db.Role;
import ua.nure.revuk.SummaryTask4.db.entity.Truck;
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
 * Epam Final Project
 * Revuk Group Java 2-1
 *
 * @author Revuk Oleksandr
 * @version 1.0
 * @since 05.02.2016
 */

/**
 * Lists trucks.
 */
public class ListTrucksCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ListTrucksCommand.class);

    private final DBManager dbManager;

    public ListTrucksCommand(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("List trucks command starts");
        // the default path of transition in case of success
        String forward = Path.PAGE_LIST_TRUCKS;

        // get this session
        HttpSession session = request.getSession();

        //get user from session
        User user = (User) session.getAttribute("user");
        LOG.trace("Get Attribute user --> " + user);

        if (user!= null && Role.getRole(user) == Role.DISPATCHER) {
            // the path of transition if user dispatcher
            forward = Path.PAGE_LIST_TRUCKS_DISP;
            LOG.debug("Role dispatcher");
        }

        //result list trucks
        List<Truck> allTrucks;

        //delete "OkMessage" from session
        UtilsWeb.removeUnwantedAttributeOkMessageFromSession(session, request);
        LOG.trace("delete \"OkMessage\" from session");

        // get filter search parameter
        String filterSearchTrucks = request.getParameter("filterSearchTrucks");
        LOG.trace("Get parameter searchParameter:" + filterSearchTrucks);

        if (filterSearchTrucks != null) {
            switch (filterSearchTrucks) {
                case "notServiceable":
                    // get all not Serviceable trucks of BD
                    allTrucks = dbManager.getAllNotServiceableTrucks();
                    LOG.debug("case notServiceable worked.");
                    break;
                case "refrigerator":
                    // get all refrigerator trucks of BD
                    allTrucks = dbManager.getAllRefrigeratorTrucks();
                    LOG.debug("case refrigerator worked.");
                    break;
                case "lorryWithSides":
                    // get all lorry wth sides trucks of BD
                    allTrucks = dbManager.getAllLorryWithSidesTrucks();
                    LOG.debug("case lorryWithSides worked.");
                    break;
                default:
                    // get all trucks of BD
                    allTrucks = dbManager.getAllTrucks();
                    LOG.debug("case default worked.");
            }
        } else {
            // get all trucks of BD
            allTrucks = dbManager.getAllTrucks();
        }

        LOG.trace("Found in DB allTrucks: " + allTrucks);

        // put truck list to request
        session.setAttribute("allTrucks", allTrucks);
        LOG.trace("Set the request attribute: allTrucks");

        LOG.debug("List trucks command finished");
        return forward;
    }
}