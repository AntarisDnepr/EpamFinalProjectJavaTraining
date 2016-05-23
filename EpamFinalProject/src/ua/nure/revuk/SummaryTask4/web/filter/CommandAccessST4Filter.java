package ua.nure.revuk.SummaryTask4.web.filter;

import org.apache.log4j.Logger;
import ua.nure.revuk.SummaryTask4.Path;
import ua.nure.revuk.SummaryTask4.db.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Security filter. Disabled by default. Uncomment Security filter
 * section in web.xml to enable.
 */
public class CommandAccessST4Filter implements Filter {

    private static final Logger LOG = Logger.getLogger(CommandAccessST4Filter.class);

    // commands access
    private Map<Role, List<String>> accessUsersMap = new HashMap<Role, List<String>>();
    private List<String> commonCommands = new ArrayList<String>();
    private List<String> outOfControlCommands = new ArrayList<String>();

    public void destroy() {
        LOG.trace("Filter destruction starts.");
        LOG.trace("Filter destruction finished.");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOG.debug("Filter starts.");

        if (accessAllowed(request)) {
            LOG.debug("Filter finished.");
            chain.doFilter(request, response);

        } else {
            String errorMessage = "You do not have permission to access the requested resource";

            request.setAttribute("errorMessage", errorMessage);
            LOG.trace("Set the request attribute: errorMessage --> " + errorMessage);

            request.getRequestDispatcher(Path.PAGE_ERROR).forward(request, response);
        }
    }

    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpReq = (HttpServletRequest) request;

        String commandName = request.getParameter("command");
        if (commandName == null || commandName.isEmpty()) {
            return false;
        }

        if (outOfControlCommands.contains(commandName)) {
            return true;
        }

        HttpSession session = httpReq.getSession(false);
        if (session == null) {
            return false;
        }

        Role userRole = (Role) session.getAttribute("userRole");
        if (userRole == null) {
            return false;
        }

        return accessUsersMap.get(userRole).contains(commandName) || commonCommands.contains(commandName);
    }

    public void init(FilterConfig fConfig) throws ServletException {
        LOG.debug("Filter initialization starts");

        // roles
        accessUsersMap.put(Role.ADMIN, asList(fConfig.getInitParameter("admin")));
        accessUsersMap.put(Role.DRIVER, asList(fConfig.getInitParameter("driver")));
        accessUsersMap.put(Role.DISPATCHER, asList(fConfig.getInitParameter("dispatcher")));
        LOG.trace("Access map --> " + accessUsersMap);

        // commonCommands
        commonCommands = asList(fConfig.getInitParameter("common"));
        LOG.trace("Common commands: " + commonCommands);

        // out of control
        outOfControlCommands = asList(fConfig.getInitParameter("out-of-control"));
        LOG.trace("Out of control commands: " + outOfControlCommands);

        LOG.debug("Filter initialization finished.");
    }

    /**
     * Extracts parameter values from string.
     *
     * @param str parameter values string.
     * @return list of parameter values.
     */
    private List<String> asList(String str) {
        List<String> list = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        return list;
    }
}