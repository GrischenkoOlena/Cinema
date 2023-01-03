package com.hryshchenko.cinema.context;

import com.hryshchenko.cinema.model.service.dbservices.CategoryService;
import com.hryshchenko.cinema.model.service.dbservices.FilmService;
import com.hryshchenko.cinema.model.service.dbservices.ScreeningService;
import com.hryshchenko.cinema.model.service.dbservices.SeatService;

public class AppContext {
    private static volatile AppContext appContext = new AppContext();
    private final ScreeningService screeningService = new ScreeningService();
    private final FilmService filmService = new FilmService();
    private final CategoryService categoryService = new CategoryService();


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
}
