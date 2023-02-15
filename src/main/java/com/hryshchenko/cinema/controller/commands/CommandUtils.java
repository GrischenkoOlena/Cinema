package com.hryshchenko.cinema.controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class CommandUtils {

    public static long getPage(HttpServletRequest req) {
        long page;
        try {
            page = Long.parseLong(req.getParameter("page"));
        } catch (NumberFormatException e){
            page = 1;
        }
        return page;
    }

    public static Locale getLocale(HttpSession session) {
        String nameLocale = (String) session.getAttribute("lang");
        Locale locale = new Locale("en");
        if (nameLocale != null && !nameLocale.isEmpty()){
            locale = new Locale(nameLocale);
        }
        return locale;
    }

}
