package com.hryshchenko.cinema.controller.commands;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.dto.FilmDTO;
import com.hryshchenko.cinema.dto.GenreDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.dbservices.GenreService;
import com.hryshchenko.cinema.model.entity.Film;
import com.hryshchenko.cinema.model.entity.Genre;
import com.hryshchenko.cinema.service.Pagination;
import com.hryshchenko.cinema.service.mapper.IMapperService;
import com.hryshchenko.cinema.service.mapper.MapperFilm;
import com.hryshchenko.cinema.service.mapper.MapperGenre;

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
        IMapperService<Film, FilmDTO> mapperServiceFilm = new MapperFilm();

        GenreService genreService = AppContext.getInstance().getGenreService();
        IMapperService<Genre, GenreDTO> mapperServiceGenre = new MapperGenre();
        try {
            List<Film> filmsList = filmsPagination.getFilmsPage(order, page);
            List<FilmDTO> films = mapperServiceFilm.getListDTO(filmsList);
            req.setAttribute("films", films);

            long countPages = filmsPagination.getCountFilmPages();
            req.setAttribute("countPages", countPages);

            List<Genre> genreList = genreService.getAllGenre();
            List<GenreDTO> genres = mapperServiceGenre.getListDTO(genreList);
            req.setAttribute("genres", genres);
        } catch (DAOException | MapperException e) {
            e.printStackTrace();
        }

        return Path.ADMIN_FILMS;
    }
}
