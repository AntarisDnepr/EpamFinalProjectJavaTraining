package ua.nure.revuk.SummaryTask4.web.command.common;

import org.apache.log4j.Logger;
import ua.nure.revuk.SummaryTask4.Path;
import ua.nure.revuk.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * PageErrorCommand
 */
public class PageErrorCommand extends Command {

    private static final Logger LOG = Logger.getLogger(PageErrorCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        LOG.debug("PageErrorCommand starts");
        LOG.debug("PageErrorCommand finished");

        return Path.PAGE_ERROR;
    }
}