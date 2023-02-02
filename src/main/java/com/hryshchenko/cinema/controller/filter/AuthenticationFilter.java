package com.hryshchenko.cinema.controller.filter;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.constant.enums.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebFilter(filterName="authentication", urlPatterns = "/controller",
        initParams = {
                @WebInitParam(name = "admin",
                        value = "customers screenings films attendance updateBalance addScreening updateScreening addFilm updateFilm"),
                @WebInitParam(name = "client", value = "tickets schedule purchase basket deleteFromBasket turnTicket"),
                @WebInitParam(name = "common", value = "logout i18n profile updateProfile"),
                @WebInitParam(name = "everybody", value = "main enter login signUp freeSeats empty basket error")
        })
public class AuthenticationFilter implements Filter {
    private static final Logger log = LogManager.getLogger();
    private static final Map<UserRole, List<String>> accessMap = new HashMap<>();
    private static List<String> common = new ArrayList<>();
    private static List<String> everybody = new ArrayList<>();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        log.debug("Filter starts");

        if (accessAllowed(request)) {
            filterChain.doFilter(request, response);
        } else {
            request.setAttribute("error", "You don't have permission to access the resource");
            log.info("error message -> You don't have permission to access the resource");
            request.getRequestDispatcher(Path.ERROR).forward(request, response);
        }
        log.debug("Filter finished");
    }

    private boolean accessAllowed(ServletRequest request) {
        String commandName = request.getParameter("action");
        HttpSession session = ((HttpServletRequest) request).getSession(false);

        if (commandName == null || commandName.isEmpty()) {
            return false;
        }
        if (everybody.contains(commandName)) {
            return true;
        }
        if (session == null) {
            return false;
        }

        UserRole userRole = (UserRole) session.getAttribute("userRole");
        if (userRole == null) {
            return false;
        }
        return accessMap.get(userRole).contains(commandName) || common.contains(commandName);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        log.debug("Filter initialization starts");

        accessMap.put(UserRole.ADMIN, asList(filterConfig.getInitParameter("admin")));
        accessMap.put(UserRole.CLIENT, asList(filterConfig.getInitParameter("client")));

        common = asList(filterConfig.getInitParameter("common"));
        everybody = asList(filterConfig.getInitParameter("everybody"));

        log.debug("Filter initialization finished");
    }

    @Override
    public void destroy() {
        log.debug("Filter destruction starts");
        log.debug("Filter destruction finished");
    }

    private List<String> asList(String param) {
        return Arrays.stream(param.split(" ")).collect(Collectors.toList());
    }
}
