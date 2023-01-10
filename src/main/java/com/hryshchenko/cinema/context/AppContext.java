package com.hryshchenko.cinema.context;

import com.hryshchenko.cinema.model.dbservices.*;

public class AppContext {
    private static volatile AppContext appContext = new AppContext();
    private final ScreeningService screeningService = new ScreeningService();
    private final FilmService filmService = new FilmService();
    private final CategoryService categoryService = new CategoryService();
    private final UserService userService = new UserService();
    private final SeatService seatService = new SeatService();
    private final TicketService ticketService = new TicketService();
    private final GenreService genreService = new GenreService();

    public static AppContext getInstance() {
        return appContext;
    }

    public ScreeningService getScreeningService() {
        return screeningService;
    }

    public FilmService getFilmService() {
        return filmService;
    }

    public SeatService getSeatService() {
        return seatService;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public UserService getUserService() {
        return userService;
    }

    public TicketService getTicketService() {
        return ticketService;
    }

    public GenreService getGenreService() {
        return genreService;
    }
}
