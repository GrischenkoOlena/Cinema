package com.hryshchenko.cinema.context;

import com.hryshchenko.cinema.model.service.dbservices.*;

public class AppContext {
    private static volatile AppContext appContext = new AppContext();
    private final ScreeningService screeningService = new ScreeningService();
    private final FilmService filmService = new FilmService();
    private final CategoryService categoryService = new CategoryService();

    private final UserService userService = new UserService();
    private final SeatService seatService = new SeatService();

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
}
