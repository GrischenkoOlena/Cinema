package com.hryshchenko.cinema.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(filterName="encoding", urlPatterns = "/*", initParams = @WebInitParam(name = "encoding", value = "UTF-8"))
public class EncodingFilter implements Filter {
    private static final Logger log = LogManager.getLogger();
    private String encoding;

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain)
            throws IOException, ServletException {
        log.debug("Filter starts");

        String requestEncoding = req.getCharacterEncoding();
        if (requestEncoding == null) {
            log.trace("Request encoding = null, set encoding --> " + encoding);
            req.setCharacterEncoding(encoding);
        }
        log.debug("Filter finished");
        filterChain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        log.debug("Filter initialization starts");
        encoding = filterConfig.getInitParameter("encoding");
        log.trace("Encoding from web.xml --> " + encoding);
        log.debug("Filter initialization finished");
    }

    @Override
    public void destroy() {
        log.debug("Filter destruction starts");
        log.debug("Filter destruction finished");
    }
}
