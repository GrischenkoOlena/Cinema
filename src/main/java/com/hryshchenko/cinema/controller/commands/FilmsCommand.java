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
import com.hryshchenko.cinema.util.OrderMapUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FilmsCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        if (req.getParameter("btnApplySort") != null) {
            session.removeAttribute("orderFilms");
        }

        String order = req.getParameter("order");
        String orderSession = (String) session.getAttribute("orderFilms");
        order = orderSession != null ? orderSession : order;

        if(order == null || order.isEmpty()){
            order = "defaultFilm";
        } else {
            session.setAttribute("orderFilms", order);
        }
        String orderBD = new OrderMapUtil().getOrderBD(order);

        if(req.getParameter("btnFilter") != null){
            session.removeAttribute("genreFilter");
        }
        Long filter;
        try {
            filter = Long.parseLong(req.getParameter("filter"));
        } catch (NumberFormatException e) {
            filter = null;
        }
        Long filterSession;
        try {
            filterSession = Long.parseLong((String) session.getAttribute("genreFilter"));
        } catch (NumberFormatException e) {
            filterSession = null;
        }
        filter = filterSession != null ? filterSession : filter;
        session.setAttribute("genreFilter", filter);

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
            List<Film> filmsList;
            long countPages;
            if(filter != null){
                filmsList = filmsPagination.getFilteredFilmsPage(filter, orderBD, page);
                countPages = filmsPagination.getCountFilteredFilmPages(filter);
            } else {
                filmsList = filmsPagination.getFilmsPage(orderBD, page);
                countPages = filmsPagination.getCountFilmPages();
            }

            List<FilmDTO> films = mapperServiceFilm.getListDTO(filmsList);
            req.setAttribute("films", films);
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
