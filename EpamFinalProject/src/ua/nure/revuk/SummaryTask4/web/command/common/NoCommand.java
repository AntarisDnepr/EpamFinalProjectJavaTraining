package ua.nure.revuk.SummaryTask4.web.command.common;

import org.apache.log4j.Logger;
import ua.nure.revuk.SummaryTask4.Path;
import ua.nure.revuk.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * No command.
 */
public class NoCommand extends Command {

    private static final Logger LOG = Logger.getLogger(NoCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        LOG.debug("No command starts");

        // set attribute errorMessage in request
        request.setAttribute("errorMessage", "No such command");
        LOG.error("Set the request attribute: errorMessage --> " + "No such command");

        LOG.debug("No command finished");
        // the default path
        return Path.PAGE_ERROR;
    }
}