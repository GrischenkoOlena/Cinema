package com.hryshchenko.cinema.controller.commands;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.constant.enums.StateScreening;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.dto.FilmDTO;
import com.hryshchenko.cinema.dto.ScreeningViewDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.dbservices.FilmService;
import com.hryshchenko.cinema.model.entity.Film;
import com.hryshchenko.cinema.model.entity.ScreeningView;
import com.hryshchenko.cinema.service.Pagination;
import com.hryshchenko.cinema.service.mapper.IMapperService;
import com.hryshchenko.cinema.service.mapper.MapperFilm;
import com.hryshchenko.cinema.service.mapper.MapperScreeningView;
import com.hryshchenko.cinema.util.OrderMapUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

public class ScreeningsCommand implements ICommand {
    private static final Logger log = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();

        if (req.getParameter("btnApplySort") != null) {
            session.removeAttribute("orderScreening");
            session.removeAttribute("filterScreening");
        }

        String order = req.getParameter("order");
        String orderSession = (String) session.getAttribute("orderScreening");
        order = orderSession != null ? orderSession : order;

        if(order == null || order.isEmpty()){
            order = "defaultScreening";
        } else {
            session.setAttribute("orderScreening", order);
        }
        String orderBD = new OrderMapUtil().getOrderBD(order);

        long page;
        try {
            page = Long.parseLong(req.getParameter("page"));
        } catch (NumberFormatException e){
            page = 1;
        }

        String filter = req.getParameter("filter");
        String filterScreening = (String) session.getAttribute("filterScreening");
        filter = filterScreening != null ? filterScreening : filter;
        if(filter != null){
            session.setAttribute("filterScreening", "checked");
        }

        LocalDate currentDate = LocalDate.now();

        Pagination screeningsPagination = new Pagination(AppContext.getInstance());
        IMapperService<ScreeningView, ScreeningViewDTO> mapperScreening = new MapperScreeningView();

        FilmService filmService = AppContext.getInstance().getFilmService();
        IMapperService<Film, FilmDTO> mapperFilm = new MapperFilm();

        try {
            List<ScreeningView> screeningsList;
            long countPages;
            if (filter == null){
                screeningsList = screeningsPagination.getScreeningViewsPage(orderBD, page);
                countPages = screeningsPagination.getCountScreeningViewPages();
            } else {
                screeningsList = screeningsPagination.getFilterScreeningViewsPage(currentDate, orderBD, page);
                countPages = screeningsPagination.getCountFilterScreeningViewPages(currentDate);
            }

            List<ScreeningViewDTO> screenings = mapperScreening.getListDTO(screeningsList);
            req.setAttribute("screenings", screenings);
            req.setAttribute("countPages", countPages);

            List<Film> filmsList = filmService.getAllFilms();
            List<FilmDTO> films = mapperFilm.getListDTO(filmsList);
            req.setAttribute("films", films);

            req.setAttribute("states", StateScreening.values());
        } catch (DAOException | MapperException e) {
            log.error(e.getMessage());
        }

        return Path.ADMIN_MAIN;
    }
}
