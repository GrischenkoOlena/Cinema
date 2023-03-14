package com.hryshchenko.cinema.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.hryshchenko.cinema.constant.FieldName.*;

/**
 * CommandUtils  class contains utils methods to use in commands.
 *
 * @author Olena Hryschenko
 */
public class CommandUtils {
    private static final Logger log = LogManager.getLogger();

    /**
     * Redirect response with catch IOException
     * @param resp response passed by command
     * @param forward redirect path
     */
    public static void sendRedirectResponse(HttpServletResponse resp, String forward) {
        try {
            resp.sendRedirect(forward);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Get number of page from request
     * @param req request passed by command
     * @return number of page (default 1)
     */
    public static long getPage(HttpServletRequest req) {
        long page;
        try {
            page = Long.parseLong(req.getParameter("page"));
        } catch (NumberFormatException e){
            page = 1;
        }
        return page;
    }

    /**
     * Get locale from session using attribute "lang"
     * @param session passed by command
     * @return Locale (default language EN)
     */
    public static Locale getLocale(HttpSession session) {
        String nameLocale = (String) session.getAttribute("lang");
        Locale locale = new Locale("en");
        if (nameLocale != null && !nameLocale.isEmpty()){
            locale = new Locale(nameLocale);
        }
        return locale;
    }

    /**
     * Get a row to sort in the database using inner static class OrderMapUtil
     * @param order passed by command
     * @return sort's string
     */
    public static String getOrderBD(String order) {
        return OrderMapUtil.getOrderBD(order);
    }

    static class OrderMapUtil {
        private final static Map<String, String> orders = new HashMap<>();
        static {
            orders.put("movieAsc", VIEW_FILM_TITLE);
            orders.put("movieDesc", VIEW_FILM_TITLE + " DESC");
            orders.put("dataAsc", VIEW_FILM_DATE);
            orders.put("dataDesc", VIEW_FILM_DATE + " DESC");
            orders.put("availableAsc", VIEW_AMOUNT_FREE_SEATS);
            orders.put("availableDesc", VIEW_AMOUNT_FREE_SEATS + " DESC");
            orders.put("defaultScreening", VIEW_FILM_DATE + " DESC");

            orders.put("nameAsc", USER_NAME);
            orders.put("nameDesc", USER_NAME + " DESC");
            orders.put("balanceAsc", USER_BALANCE);
            orders.put("balanceDesc", USER_BALANCE + " DESC");
            orders.put("defaultCustomer", USER_ID);

            orders.put("titleAsc", FILM_TITLE);
            orders.put("titleDesc", FILM_TITLE + " DESC");
            orders.put("durationAsc", FILM_DURATION);
            orders.put("durationDesc", FILM_DURATION + " DESC");
            orders.put("defaultFilm", FILM_ID);

            orders.put("dataTicketAsc", SCREENING_ID);
            orders.put("dataTicketDesc", SCREENING_ID + " DESC");
            orders.put("defaultTicket", TICKET_ID + " DESC");
        }

        public static String getOrderBD(String order) {
            return "ORDER BY " + orders.get(order);
        }
    }
}
