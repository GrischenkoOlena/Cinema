package com.hryshchenko.cinema.constant;

public interface Path {
    String HOME = "index.jsp";
    String MAIN = "/WEB-INF/main.jsp";
    String LOGIN = "/WEB-INF/login.jsp";
    String SIGN_UP = "/WEB-INF/signUp.jsp";
    String FREE_SEAT = "/WEB-INF/freeSeat.jsp";
    String ERROR = "/WEB-INF/errors/error500.jsp";
    String SUCCESS = "/WEB-INF/success.jsp";
    String USER_MAIN = "/WEB-INF/user/userMain.jsp";
    String ADMIN_MAIN = "/WEB-INF/admin/adminMain.jsp";

    String PROFILE = "WEB-INF/profile.jsp";
    String USER_TICKETS = "/WEB-INF/user/tickets.jsp";
    String ADMIN_FILMS = "/WEB-INF/admin/films.jsp";
    String ADMIN_CUSTOMERS = "/WEB-INF/admin/customers.jsp";
    String ADMIN_ATTENDANCE = "/WEB-INF/admin/attendance.jsp";
    String TICKER_BASKET = "/WEB-INF/user/basket.jsp";

    String COMMAND_REDIRECT = "redirect";
    String COMMAND_USER_SCHEDULE = "controller?action=schedule";
    String COMMAND_ADMIN_SCREENINGS = "controller?action=screenings";
    String COMMAND_ADMIN_CUSTOMERS = "controller?action=customers";
    String COMMAND_ADMIN_FILMS = "controller?action=films";
    String COMMAND_FREE_SEATS = "controller?action=freeSeats";
    String COMMAND_USER_TICKETS = "controller?action=tickets";

    String COMMAND_ERROR = "controller?action=error";

    String COMMAND_PROFILE = "controller?action=profile";

}
