package com.hryshchenko.cinema.service;

import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.dbservices.FilmService;
import com.hryshchenko.cinema.model.dbservices.ScreeningService;
import com.hryshchenko.cinema.model.dbservices.TicketService;
import com.hryshchenko.cinema.model.dbservices.UserService;
import com.hryshchenko.cinema.model.entity.Film;
import com.hryshchenko.cinema.model.entity.Screening;
import com.hryshchenko.cinema.model.entity.Ticket;
import com.hryshchenko.cinema.model.entity.User;

import java.util.List;

public class Pagination {
    private static final long ON_PAGE = 5;
    private final AppContext appContext;

    public Pagination(AppContext appContext) {
        this.appContext = appContext;
    }

    public long getCountScreeningPages() throws DAOException {
        ScreeningService screeningsServ = appContext.getScreeningService();
        long countValue = screeningsServ.getCountScreening();
        return (long) Math.ceil(countValue * 1.0/ON_PAGE);
    }

    public List<Screening> getScreeningsPage(long numberPage) throws DAOException {
        ScreeningService screeningsServ = appContext.getScreeningService();
        long begin = ((numberPage - 1) * ON_PAGE + 1);
        return screeningsServ.getScreeningsPage(begin, ON_PAGE);
    }

    public long getCountFilmPages() throws DAOException {
        FilmService filmService = appContext.getFilmService();
        long countValue = filmService.getCountFilms();
        return (long) Math.ceil(countValue * 1.0/ON_PAGE);
    }

    public List<Film> getFilmsPage(long numberPage) throws DAOException {
        FilmService filmService = appContext.getFilmService();
        long begin = ((numberPage - 1) * ON_PAGE + 1);
        return filmService.getFilmsPage(begin, ON_PAGE);
    }

    public long getCountUserPages() throws DAOException {
        UserService userService = appContext.getUserService();
        long countValue = userService.getCountUsers();
        return (long) Math.ceil(countValue * 1.0/ON_PAGE);
    }

    public List<User> getUsersPage(long numberPage) throws DAOException {
        UserService userService = appContext.getUserService();
        long begin = ((numberPage - 1) * ON_PAGE + 1);
        return userService.getUsersPage(begin, ON_PAGE);
    }

    public long getCountTicketPagesByUser(User user) throws DAOException {
        TicketService ticketService = appContext.getTicketService();
        long countValue = ticketService.getCountTicketByUser(user);
        return (long) Math.ceil(countValue * 1.0/ON_PAGE);
    }

    public List<Ticket> getTicketsPageByUser(User user, long numberPage) throws DAOException {
        TicketService ticketService = appContext.getTicketService();
        long begin = ((numberPage - 1) * ON_PAGE + 1);
        return ticketService.getTicketsPageByUser(user, begin, ON_PAGE);
    }

}
