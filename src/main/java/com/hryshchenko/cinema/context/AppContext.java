package com.hryshchenko.cinema.context;

import com.hryshchenko.cinema.model.service.FilmService;
import com.hryshchenko.cinema.model.service.ScreeningService;

public class AppContext {
    private static volatile AppContext appContext = new AppContext();
    private final ScreeningService screeningService = new ScreeningService();
    private final FilmService filmService = new FilmService();

    public static AppContext getInstance() {
        return appContext;
    }

    public ScreeningService getScreeningService() {
        return screeningService;
    }

    public FilmService getFilmService() {
        return filmService;
    }
}
