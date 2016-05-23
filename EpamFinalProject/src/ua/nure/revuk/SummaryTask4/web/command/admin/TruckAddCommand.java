package ua.nure.revuk.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.revuk.SummaryTask4.Path;
import ua.nure.revuk.SummaryTask4.db.DBManager;
import ua.nure.revuk.SummaryTask4.db.entity.Truck;
import ua.nure.revuk.SummaryTask4.db.util.ValidUtils;
import ua.nure.revuk.SummaryTask4.exception.AppException;
import ua.nure.revuk.SummaryTask4.web.UtilsWeb;
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
 * Truck Add
 */
public class TruckAddCommand extends Command {

    private static final Logger LOG = Logger.getLogger(TruckAddCommand.class);

    private final DBManager dbManager;

    public TruckAddCommand(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Truck add command starts");
        // the default path of transition in case of success
        String forward = Path.PAGE_TRUCK_ADD;

        // get this session
        HttpSession session = request.getSession();
        // insert in the database flag

        UtilsWeb.removeUnwantedAttributeOkMessageFromSession(session, request);
        String insertToBD = request.getParameter("insertToBD");


        // validation checks parameters to insert to BD
        if (insertToBD != null && insertToBD.equals("insertToBD")) {
            LOG.trace("Flag of insert to BD.");

            // parameter truckName.
            String truckName = request.getParameter("truckName");
            LOG.debug("parameter of truckName: " + truckName);
            // parameter carrying.
            String carrying = request.getParameter("carrying");
            LOG.debug("parameter of carrying: " + carrying);
            // parameter capacity.
            String capacity = request.getParameter("capacity");
            LOG.debug("parameter of capacity: " + capacity);
            // parameter length.
            String length = request.getParameter("length");
            LOG.debug("parameter of length: " + length);
            // parameter lorryWithSides.
            String lorryWithSides = request.getParameter("lorryWithSides");
            LOG.debug("parameter of lorryWithSides: " + lorryWithSides);
            // parameter refrigerator.
            String refrigerator = request.getParameter("refrigerator");
            LOG.debug("parameter of refrigerator: " + refrigerator);
            // parameter serviceable.
            String serviceable = request.getParameter("serviceable");
            LOG.debug("parameter of serviceable: " + serviceable);
            // parameter photoLink.
            String photoLink = request.getParameter("photoLink");
            LOG.debug("parameter of photoLink: " + photoLink);

            // if parameter of photoLink empty - set default value
            if (photoLink == null || photoLink.equals("")) {
                LOG.debug("parameter of photoLink empty. Set default value");
                photoLink = "truck.jpg";
            }

            // validation checks parameters
            if (ValidUtils.inputValidCharacteristicTruck(carrying, capacity, length).equals("ok") &&
                    ValidUtils.isBoolean(lorryWithSides, refrigerator, serviceable) &&
                    ValidUtils.validTruckName(truckName) && ValidUtils.validPhotoLinkNameFile(photoLink)) {

                LOG.debug("input parameters truck validation - ok");

                Truck truck = new Truck();

                // set truckName
                truck.setTruckName(truckName);
                // set carrying, if the user enters values
                if (carrying != null && !carrying.equals("")) {
                    truck.setCarrying(Double.valueOf(carrying));
                }
                // set capacity, if the user enters values
                if (capacity != null && !capacity.equals("")) {
                    truck.setCapacity(Double.valueOf(capacity));
                }
                // set length, if the user enters values
                if (length != null && !length.equals("")) {
                    truck.setLength(Double.valueOf(length));
                }
                // set lorryWithSides
                truck.setLorryWithSides(Boolean.valueOf(lorryWithSides));
                // set refrigerator
                truck.setRefrigerator(Boolean.valueOf(refrigerator));
                // set serviceable
                truck.setServiceable(Boolean.valueOf(serviceable));
                // set photoLink
                truck.setPhotoLink(photoLink);

                LOG.debug("prepared truck input in DB: " + truck);

                // insert "truck" in the BD
                // successfulExecutionUpdate - successful execution must be 1
                int successfulExecutionAdd = dbManager.insertTruck(truck);
                LOG.debug("insert truck in DB. Successful execution must be 1 --> " + successfulExecutionAdd);

                // put "okMessage" to request
                session.setAttribute("okMessage", "okMessageTruckAdd");
                LOG.trace("Set the request attribute: okMessage --> " + "okMessageTruckAdd");

                // path of transition in case of success
                forward = Path.COMMAND_LIST_TRUCKS;

            } else {
                LOG.error("input parameters of truck form incorrect!");
                throw new AppException("input parameters of truck form incorrect!");
            }
        }

        LOG.debug("Truck add command finished");
        return forward;
    }
}