package com.hryshchenko.cinema.controller.commands.admin;

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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FilmsCommand implements ICommand {
    private static final Logger log = LogManager.getLogger();

    private final Pagination filmsPagination = new Pagination(AppContext.getInstance());
    private final IMapperService<Film, FilmDTO> mapperServiceFilm = new MapperFilm();
    private final GenreService genreService = AppContext.getInstance().getGenreService();
    private final IMapperService<Genre, GenreDTO> mapperServiceGenre = new MapperGenre();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        if (req.getParameter("btnApplySort") != null) {
            session.removeAttribute("orderFilms");
        }

        String orderBD = getOrderBD(req, session);

        if(req.getParameter("btnFilter") != null){
            session.removeAttribute("genreFilter");
        }
        Long filter = getFilter(req, session);
        session.setAttribute("genreFilter", filter);

        long page = getPage(req);

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
            log.error(e.getMessage());
        }

        return Path.ADMIN_FILMS;
    }

    private String getOrderBD(HttpServletRequest req, HttpSession session) {
        String order = req.getParameter("order");
        String orderSession = (String) session.getAttribute("orderFilms");
        order = orderSession != null ? orderSession : order;

        if(order == null || order.isEmpty()){
            order = "defaultFilm";
        } else {
            session.setAttribute("orderFilms", order);
        }
        return new OrderMapUtil().getOrderBD(order);
    }

    private Long getFilter(HttpServletRequest req, HttpSession session) {
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
        return filter;
    }

    private long getPage(HttpServletRequest req) {
        long page;
        try {
            page = Long.parseLong(req.getParameter("page"));
        } catch (NumberFormatException e){
            page = 1;
        }
        return page;
    }
}
