package ua.nure.revuk.SummaryTask4.web.command.dispatcher;

import org.apache.log4j.Logger;
import ua.nure.revuk.SummaryTask4.Path;
import ua.nure.revuk.SummaryTask4.db.DBManager;
import ua.nure.revuk.SummaryTask4.db.bean.RequestUserBean;
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
 * Lists requests.
 */
public class ListRequestsCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ListRequestsCommand.class);

    private final DBManager dbManager;

    public ListRequestsCommand(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("List requests command starts");
        // the default path of transition in case of success
        String forward = Path.PAGE_LIST_REQUESTS;

        // get this session
        HttpSession session = request.getSession();

        //delete "OkMessage" from session
        UtilsWeb.removeUnwantedAttributeOkMessageFromSession(session, request);
        LOG.trace("delete \"OkMessage\" from session");

        // get all requests from the database
        List<RequestUserBean> allRequests = dbManager.getAllRequests();
        LOG.trace("Found in DB: allRequests --> " + allRequests);

        // put user order beans list to request
        session.setAttribute("allRequests", allRequests);
        LOG.trace("Set the request attribute: allRequests");

        LOG.debug("List requests command finished");
        return forward;
    }

}