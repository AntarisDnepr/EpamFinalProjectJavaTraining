package ua.nure.revuk.SummaryTask4.web.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Encoding filter.
 */
public class EncodingST4Filter implements Filter {

    private static final Logger LOG = Logger.getLogger(EncodingST4Filter.class);

    private String encodingST4;

    public void destroy() {
        LOG.trace("Filter destruction starts.");
        LOG.trace("Filter destruction finished.");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        LOG.trace("Filter starts.");

        HttpServletRequest httpRequestEn = (HttpServletRequest) request;
        LOG.trace("Request uri: " + httpRequestEn.getRequestURI());

        String requestEncoding = request.getCharacterEncoding();
        if (requestEncoding == null) {
            LOG.trace("Request encodingST4 = null, set encodingST4 --> " + encodingST4);
            request.setCharacterEncoding(encodingST4);
        }

        LOG.debug("Filter finished");
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
        LOG.debug("Filter initialization starts.");
        encodingST4 = fConfig.getInitParameter("encoding");
        LOG.trace("Encoding from web.xml --> " + encodingST4);
        LOG.debug("Filter initialization finished.");
    }
}