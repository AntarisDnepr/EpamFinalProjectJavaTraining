package ua.nure.revuk.SummaryTask4.web.command.common;

import org.apache.log4j.Logger;
import ua.nure.revuk.SummaryTask4.Path;
import ua.nure.revuk.SummaryTask4.exception.AppException;
import ua.nure.revuk.SummaryTask4.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;


/**
 * Change Locale.
 */
public class ChangeLocaleCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ChangeLocaleCommand.class);

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command change locale starts");
        // the default path of transition in case of success
        String forward = Path.PAGE_SETTINGS;

        // get this session
        HttpSession session = request.getSession();

        // get parameter locale
        String locale = request.getParameter("locale");
        LOG.debug("get parameter locale: " + locale);

        // change locale
        Config.set(session, Config.FMT_LOCALE, new java.util.Locale(locale));

        //set attribute currentLocale
        session.setAttribute("currentLocale", locale);
        LOG.debug("set attribute currentLocale: " + locale);

        LOG.debug("Command change locale finished");
        return forward;
    }
}