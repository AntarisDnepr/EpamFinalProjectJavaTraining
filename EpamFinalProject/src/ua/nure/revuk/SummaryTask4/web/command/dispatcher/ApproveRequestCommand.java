package ua.nure.revuk.SummaryTask4.web.command.dispatcher;

import org.apache.log4j.Logger;
import ua.nure.revuk.SummaryTask4.Path;
import ua.nure.revuk.SummaryTask4.db.DBManager;
import ua.nure.revuk.SummaryTask4.db.bean.RequestUserBean;
import ua.nure.revuk.SummaryTask4.db.entity.Truck;
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

/**
 * Epam Final Project
 * Revuk Group Java 2-1
 *
 * @author Revuk Oleksandr
 * @version 1.0
 * @since 05.02.2016
 */

/**
 * Approve request.
 */
public class ApproveRequestCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ApproveRequestCommand.class);

    private final DBManager dbManager;

    public ApproveRequestCommand(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Approve request command starts");
        // the default path of transition in case of success
        String forward = Path.PAGE_APPROVE_REQUEST;

        // get this session
        HttpSession session = request.getSession();

        // insert in the database flag
        String insertToBD = request.getParameter("insertToBD");

        // validation checks parameters to insert to BD
        if (insertToBD != null && insertToBD.equals("insertToBD")) {
            LOG.trace("Flag of insert to BD.");

            // get user from session
            User user = (User) session.getAttribute("user");
            LOG.debug("Get Attribute user: " + user);

            //check valid user
            if (user == null) {
                LOG.error("User do not have permission to access the requested resource");
                throw new AppException("You do not have permission to access the requested resource.");
            }

            // get attribute requestUB
            RequestUserBean requestUB = (RequestUserBean) session.getAttribute("approveRequest");
            LOG.trace("get attribute approveRequest: " + requestUB);
            // get parameter approveRequestTruckId
            String approveRequestTruckId = request.getParameter("approveRequestTruckId");
            LOG.trace("get attribute approveRequestTruckId: " + approveRequestTruckId);

            // validation checks parameters approveRequestTruckId - is a number. If not - throw exception
            if (!ValidUtils.isNumber(approveRequestTruckId)) {
                LOG.error("incorrect parameter approveRequestTruckId !");
                throw new AppException("incorrect parameter approveRequestTruckId !");
            }

            // update flight (approve request).From id flight, driver id, truck id.
            // successfulExecutionUpdate - successful execution must be 1
            int successfulExecutionUpdate = dbManager.updateFlightApproveRequest(
                    requestUB.getBeanFlightId(), requestUB.getBeanDriverId(),
                    Integer.valueOf(approveRequestTruckId), user.getId());
            LOG.trace("delete truck in DB: successful execution must be 1 --> " + successfulExecutionUpdate);

            // put "okMessage" to request
            session.setAttribute("okMessage", "okMessageRequestApprove");
            LOG.trace("Set the request attribute: okMessage --> " + "okMessageRequestApprove");

            // path of transition in case of success
            forward = Path.COMMAND_LIST_REQUEST;

        } else {
            // get attribute allRequests
            List<RequestUserBean> allRequests = (ArrayList) session.getAttribute("allRequests");
            LOG.trace("get attribute allRequests: " + allRequests);
            // get parameter approveRequestId
            String approveRequestId = request.getParameter("approveRequestId");
            LOG.trace("get attribute approveRequestId: " + approveRequestId);

            // validation checks parameters approveRequestId - is a number. If not - throw exception
            if (allRequests != null && approveRequestId != null && ValidUtils.isNumber(approveRequestId)) {
                // find request for id of all request
                for (RequestUserBean requestUserBean : allRequests) {
                    if (requestUserBean.getId() == Integer.valueOf(approveRequestId)) {

                        // search trucks in the set parameters
                        List<Truck> allTrucks = dbManager.getAllTrucksWithParameters(
                                requestUserBean.getBeanCarrying(), requestUserBean.getBeanCapacity(),
                                requestUserBean.getBeanLength(), requestUserBean.isBeanLorryWithSides(),
                                requestUserBean.isBeanRefrigerator());

                        // set attribute "allTrucks". All trucks in the set parameters
                        session.setAttribute("allTrucks", allTrucks);
                        LOG.trace("Set the request attribute: allTrucks --> " + allTrucks);

                        // set attribute "approveRequest". This Request (RequestUserBean)
                        session.setAttribute("approveRequest", requestUserBean);
                        LOG.trace("Set the request attribute: approveRequest --> " + requestUserBean);

                        // set attribute "approveRequestId". This id from request
                        session.setAttribute("approveRequestId", approveRequestId);
                        LOG.trace("Set the request attribute: approveRequestId --> " + approveRequestId);
                        break;
                    }
                }
            } else {
                LOG.error("input parameters of approve request incorrect!");
                throw new AppException("input parameters of approve request incorrect!");
            }
        }

        LOG.debug("Approve request command finished");
        return forward;
    }
}