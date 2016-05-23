package ua.nure.revuk.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.revuk.SummaryTask4.Path;
import ua.nure.revuk.SummaryTask4.db.DBManager;
import ua.nure.revuk.SummaryTask4.db.entity.Truck;
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
 * Edit truck.
 */
public class EditTruckCommand extends Command {

    private static final Logger LOG = Logger.getLogger(EditTruckCommand.class);

    private final DBManager dbManager;

    public EditTruckCommand(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Edit truck command starts");
        // the default path of transition in case of success
        String forward = Path.PAGE_EDIT_TRUCK;
        // get this session
        HttpSession session = request.getSession();

        // the id of truck to be edited
        String editTruckId = request.getParameter("editTruckId");
        LOG.debug("Truck id: " + editTruckId);

        // insert in the database flag
        String insertToBD = request.getParameter("insertToBD");

        //  get user of request
        Truck editTruck = (Truck) session.getAttribute("editTruck");
        LOG.trace("Get the request attribute: editTruck --> " + editTruck);

        // validation checks parameters to insert to BD
        if (editTruck != null & insertToBD != null && insertToBD.equals("insertToBD")) {

            // parameter truckName. If the user does not enter anything, the previous value is set
            String truckName = request.getParameter("truckName");
            if (truckName == null || truckName.equals("")) {
                truckName = editTruck.getTruckName();
            }
            LOG.debug("parameter of truckName: " + truckName);

            // parameter carrying. If the user does not enter anything, the previous value is set
            String carrying = request.getParameter("carrying");
            if (carrying == null || carrying.equals("")) {
                carrying = String.valueOf(editTruck.getCarrying());
            }
            LOG.debug("parameter of carrying: " + carrying);

            // parameter capacity. If the user does not enter anything, the previous value is set
            String capacity = request.getParameter("capacity");
            if (capacity == null || capacity.equals("")) {
                capacity = String.valueOf(editTruck.getCapacity());
            }
            LOG.debug("parameter of capacity: " + capacity);

            // parameter length. If the user does not enter anything, the previous value is set
            String length = request.getParameter("length");
            if (length == null || length.equals("")) {
                length = String.valueOf(editTruck.getLength());
            }
            LOG.debug("parameter of length: " + length);

            // parameter lorryWithSides. If the user does not enter anything, the previous value is set
            String lorryWithSides = request.getParameter("lorryWithSides");
            if (lorryWithSides == null || lorryWithSides.equals("")) {
                lorryWithSides = String.valueOf(editTruck.isLorryWithSides());
            }
            LOG.debug("parameter of lorryWithSides: " + lorryWithSides);

            // parameter refrigerator. If the user does not enter anything, the previous value is set
            String refrigerator = request.getParameter("refrigerator");
            if (refrigerator == null || refrigerator.equals("")) {
                refrigerator = String.valueOf(editTruck.isRefrigerator());
            }
            LOG.debug("parameter of refrigerator: " + refrigerator);

            // parameter serviceable. If the user does not enter anything, the previous value is set
            String serviceable = request.getParameter("serviceable");
            if (serviceable == null || serviceable.equals("")) {
                serviceable = String.valueOf(editTruck.isServiceable());
            }
            LOG.debug("parameter of serviceable: " + serviceable);

            // parameter photoLink. If the user does not enter anything, the previous value is set
            String photoLink = request.getParameter("photoLink");
            if (photoLink == null || photoLink.equals("")) {
                photoLink = editTruck.getPhotoLink();
            }
            LOG.debug("parameter of photoLink: " + photoLink);

            // validation checks parameters
            if (ValidUtils.inputValidCharacteristicTruck(carrying, capacity, length).equals("ok") &&
                    ValidUtils.isBoolean(lorryWithSides, refrigerator, serviceable) &&
                    ValidUtils.validTruckName(truckName) && ValidUtils.validPhotoLinkNameFile(photoLink)) {

                LOG.debug("input parameters truck validation - ok");

                editTruck.setTruckName(truckName);
                if (!carrying.equals("")) {
                    editTruck.setCarrying(Double.valueOf(carrying));
                }
                if (!capacity.equals("")) {
                    editTruck.setCapacity(Double.valueOf(capacity));
                }
                if (!length.equals("")) {
                    editTruck.setLength(Double.valueOf(length));
                }
                editTruck.setLorryWithSides(Boolean.valueOf(lorryWithSides));
                editTruck.setRefrigerator(Boolean.valueOf(refrigerator));
                editTruck.setServiceable(Boolean.valueOf(serviceable));
                editTruck.setPhotoLink(photoLink);
                LOG.debug("prepared truck input in DB: " + editTruck);

                // update truck in BD
                // successfulExecutionUpdate - successful execution must be 1
                int successfulExecutionAdd = dbManager.updateTruckById(editTruck);
                LOG.debug("update truck in DB. Successful execution must be 1 --> " + successfulExecutionAdd);

                //  put "okMessage" to request
                session.setAttribute("okMessage", "okMessageTruckEdit");
                LOG.trace("Set the request attribute: okMessage --> " + "okMessageTruckEdit");

                // path of transition in case of success
                forward = Path.COMMAND_LIST_TRUCKS;

            } else {
                LOG.error("input parameters of truck form incorrect!");
                throw new AppException("input parameters of truck form incorrect!");
            }
        } else if (ValidUtils.isNumber(editTruckId)) {
            // get truck by id
            editTruck = dbManager.getTruckById(Integer.valueOf(editTruckId));
            LOG.trace("Found in DB: truck --> " + editTruck);

            //  put truck to request
            session.setAttribute("editTruck", editTruck);
            LOG.trace("Set the request attribute: editTruck --> " + editTruck);

        } else {
            LOG.error("input parameters of truck incorrect!");
            throw new AppException("input parameters of truck incorrect!");
        }

        LOG.debug("Edit truck command finished");
        return forward;
    }
}