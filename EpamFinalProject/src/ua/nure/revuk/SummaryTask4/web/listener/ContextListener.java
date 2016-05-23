package ua.nure.revuk.SummaryTask4.web.listener;

import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Context listener.
 */
public class ContextListener implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(ContextListener.class);

    public void contextDestroyed(ServletContextEvent event) {
        LOG.info("Servlet context destruction starts.");
        LOG.info("Servlet context destruction finished.");
    }

    public void contextInitialized(ServletContextEvent event) {
        LOG.info("Servlet context initialization starts");

        ServletContext servletContext = event.getServletContext();
        String localesFileName = servletContext.getInitParameter("locales");

        // obtain real path on server
        String localesFileRealPath = servletContext.getRealPath(localesFileName);

        // local descriptions
        Properties locales = new Properties();
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(localesFileRealPath);
            locales.load(fis);
        } catch (IOException e) {
            LOG.error("locales IOException!");
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex) {
                LOG.error("Not close FileInputStream!");
            }
        }

        // save descriptions to servlet context
        servletContext.setAttribute("locales", locales);
        locales.list(System.out);

        initCommandContainer();
        LOG.info("Servlet context initialization finished");
    }

    /**
     * Initializes CommandContainer.
     */
    private void initCommandContainer() {

        // initialize commands container
        // just load class to JVM
        try {
            Class.forName("ua.nure.revuk.SummaryTask4.web.command.CommandContainer");
        } catch (ClassNotFoundException ex) {
            LOG.error("Cannot initialize Command Container!");
            throw new IllegalStateException("Cannot initialize Command Container");
        }
    }
}