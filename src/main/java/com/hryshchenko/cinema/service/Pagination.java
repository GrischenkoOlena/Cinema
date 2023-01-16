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

import java.time.LocalDate;
import java.util.List;

public class Pagination {
    private static final long ON_PAGE = 5;
    private final TicketService ticketService;
    private final ScreeningService screeningsServ;
    private final FilmService filmService;
    private final UserService userService;

    public Pagination(AppContext appContext) {
        ticketService = appContext.getTicketService();
        screeningsServ = appContext.getScreeningService();
        filmService = appContext.getFilmService();
        userService = appContext.getUserService();
    }

    public long getCountScreeningPages() throws DAOException {
        long countValue = screeningsServ.getCountScreening();
        return getCountPages(countValue);
    }

    public List<Screening> getScreeningsPage(String order, long numberPage) throws DAOException {
        long begin = getBegin(numberPage);
        return screeningsServ.getScreeningsPage(order, begin, ON_PAGE);
    }

    public long getCountFilterScreeningPages(LocalDate filterDate) throws DAOException {
        long countValue = screeningsServ.getCountAvailableScreening(filterDate);
        return getCountPages(countValue);
    }

    public List<Screening> getFilterScreeningsPage(LocalDate filterDate, String order, long numberPage) throws DAOException {
        long begin = getBegin(numberPage);
        return screeningsServ.getAvailableScreeningsPage(filterDate, order, begin, ON_PAGE);
    }

    public long getCountFilmPages() throws DAOException {
        long countValue = filmService.getCountFilms();
        return getCountPages(countValue);
    }

    public List<Film> getFilmsPage(String order, long numberPage) throws DAOException {
        long begin = getBegin(numberPage);
        return filmService.getFilmsPage(order, begin, ON_PAGE);
    }

    public long getCountFilteredFilmPages(long genreId) throws DAOException {
        long countValue = filmService.getCountFilmsByGenre(genreId);
        return getCountPages(countValue);
    }

    public List<Film> getFilteredFilmsPage(long genreId, String order, long numberPage) throws DAOException {
        long begin = getBegin(numberPage);
        return filmService.getFilmsPageByGenre(genreId, order, begin, ON_PAGE);
    }

    public long getCountUserPages() throws DAOException {
        long countValue = userService.getCountUsers();
        return getCountPages(countValue);
    }

    public List<User> getUsersPage(String order, long numberPage) throws DAOException {
        long begin = getBegin(numberPage);
        return userService.getUsersPage(order, begin, ON_PAGE);
    }

    public long getCountTicketPagesByUser(long userId) throws DAOException {
        long countValue = ticketService.getCountTicketByUser(userId);
        return getCountPages(countValue);
    }

    public List<Ticket> getTicketsPageByUser(String order, long userId, long numberPage) throws DAOException {
        long begin = getBegin(numberPage);
        return ticketService.getTicketsPageByUser(order, userId, begin, ON_PAGE);
    }

    public long getCountTicketPagesByUserDate(long userId, LocalDate date) throws DAOException {
        long countValue = ticketService.getCountTicketByUserDate(userId, date);
        return getCountPages(countValue);
    }

    public List<Ticket> getTicketsPageByUserDate(String order, long userId, LocalDate date, long numberPage)
            throws DAOException {
        long begin = getBegin(numberPage);
        return ticketService.getTicketsPageByUserDate(order, userId, date, begin, ON_PAGE);
    }

    private long getCountPages(long countValue) {
        return (long) Math.ceil(countValue * 1.0 / ON_PAGE);
    }

    private long getBegin(long numberPage) {
        return ((numberPage - 1) * ON_PAGE + 1);
    }

}
