package ua.nure.revuk.SummaryTask4.web;

import org.apache.log4j.Logger;
import ua.nure.revuk.SummaryTask4.exception.AppException;
import ua.nure.revuk.SummaryTask4.web.command.Command;
import ua.nure.revuk.SummaryTask4.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
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
 * @since 02.02.2016
 * <p>
 * Main servlet controller.
 */
public class Controller extends HttpServlet {

    private static final long serialVersionUID = -8136146078011965923L;

    private static final Logger LOG = Logger.getLogger(Controller.class);

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    /**
     * Main method of this controller.
     */
    private void process(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {

        // get this session
        HttpSession session = request.getSession();

        // extract command name from the request
        String commandName = request.getParameter("command");
        LOG.trace("Request parameter: command --> " + commandName);

        // obtain command object by its name
        Command command = CommandContainer.get(commandName);
        LOG.trace("Obtained command --> " + command);

        // execute command and get forward address
        String forward;
        try {
            forward = command.execute(request, response);
        } catch (AppException ex) {
            session.setAttribute("errorMessage", ex.getMessage());
            LOG.error("errorMessage --> " + ex.getMessage());
            forward = "/controller?command=pageError";
        }

        LOG.debug("Controller finished, now go to forward address --> " + forward);

        // go to forward
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            request.getRequestDispatcher(forward).forward(request, response);
        } else {
            response.sendRedirect(forward);
        }
    }
}