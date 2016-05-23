package ua.nure.revuk.SummaryTask4.web.command.common;

import org.apache.log4j.Logger;
import ua.nure.revuk.SummaryTask4.Path;
import ua.nure.revuk.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * View settings command.
 */
public class ViewSettingsCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ViewSettingsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        LOG.debug("Command View Settings starts");
        LOG.debug("Command View Settings finished");

        return Path.PAGE_SETTINGS;
    }
}