package com.hryshchenko.cinema.controller.commands;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.dto.FilmDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.entity.Film;
import com.hryshchenko.cinema.service.Pagination;
import com.hryshchenko.cinema.service.mapper.IMapperService;
import com.hryshchenko.cinema.service.mapper.MapperFilm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FilmsCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String order = req.getParameter("order");
        if(order == null){
            order = "film_id";
        }

        long page;
        try {
            page = Long.parseLong(req.getParameter("page"));
        } catch (NumberFormatException e){
            page = 1;
        }

        Pagination filmsPagination = new Pagination(AppContext.getInstance());
        IMapperService<Film, FilmDTO> mapperService = new MapperFilm();
        try {
            List<Film> filmsList = filmsPagination.getFilmsPage(order, page);
            List<FilmDTO> films = mapperService.getListDTO(filmsList);
            req.setAttribute("films", films);

            long countPages = filmsPagination.getCountFilmPages();
            req.setAttribute("countPages", countPages);
        } catch (DAOException | MapperException e) {
            e.printStackTrace();
        }

        return Path.ADMIN_FILMS;
    }
}
