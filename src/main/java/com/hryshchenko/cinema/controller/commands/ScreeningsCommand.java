package com.hryshchenko.cinema.controller.commands;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.constant.enums.StateScreening;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.dto.FilmDTO;
import com.hryshchenko.cinema.dto.ScreeningDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.dbservices.FilmService;
import com.hryshchenko.cinema.model.entity.Film;
import com.hryshchenko.cinema.model.entity.Screening;
import com.hryshchenko.cinema.service.Pagination;
import com.hryshchenko.cinema.service.mapper.IMapperService;
import com.hryshchenko.cinema.service.mapper.MapperFilm;
import com.hryshchenko.cinema.service.mapper.MapperScreening;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;

public class ScreeningsCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String order = req.getParameter("order");
        if(order == null){
            order = "film_date DESC";
        }
        String filter = req.getParameter("filter");

        long page;
        try {
            page = Long.parseLong(req.getParameter("page"));
        } catch (NumberFormatException e){
            page = 1;
        }

        LocalDate currentDate = LocalDate.now();

        Pagination screeningsPagination = new Pagination(AppContext.getInstance());
        FilmService filmService = AppContext.getInstance().getFilmService();
        IMapperService<Screening, ScreeningDTO> mapperScreening = new MapperScreening();
        IMapperService<Film, FilmDTO> mapperFilm = new MapperFilm();

        try {
            List<Screening> screeningsList = screeningsPagination.getScreeningsPage(order, page);
            List<ScreeningDTO> screenings = mapperScreening.getListDTO(screeningsList);
            req.setAttribute("screenings", screenings);

            long countPages = screeningsPagination.getCountScreeningPages();
            req.setAttribute("countPages", countPages);

            List<Film> filmsList = filmService.getAllFilms();
            List<FilmDTO> films = mapperFilm.getListDTO(filmsList);
            req.setAttribute("films", films);

            req.setAttribute("states", StateScreening.values());
        } catch (DAOException | MapperException e) {
            e.printStackTrace();
        }

        return Path.ADMIN_MAIN;
    }
}
