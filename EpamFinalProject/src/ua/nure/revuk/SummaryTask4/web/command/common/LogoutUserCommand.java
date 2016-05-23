package ua.nure.revuk.SummaryTask4.web.command.common;

import org.apache.log4j.Logger;
import ua.nure.revuk.SummaryTask4.Path;
import ua.nure.revuk.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Logout command.
 */
public class LogoutUserCommand extends Command {

    private static final Logger LOG = Logger.getLogger(LogoutUserCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        LOG.debug("Command logout starts");
        // get this session
        HttpSession session = request.getSession();

        //check session
        if (session != null) {
            session.invalidate();
            LOG.debug("Session invalidate");
        }

        LOG.debug("Command logout finished");

        // the default path of transition in case of success
        return Path.PAGE_LOGIN;
    }
}