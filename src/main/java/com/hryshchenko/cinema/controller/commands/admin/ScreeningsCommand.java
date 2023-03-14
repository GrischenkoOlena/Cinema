package com.hryshchenko.cinema.controller.commands.admin;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.constant.enums.StateScreening;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.controller.commands.CommandUtils;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

/**
 *  Command for view screenings. Available to the administrator.
 *
 *  @author Olena Hryshchenko
 */
public class ScreeningsCommand implements ICommand {
    private static final Logger log = LogManager.getLogger();

    private final Pagination screeningsPagination = new Pagination(AppContext.getInstance());
    private final IMapperService<ScreeningView, ScreeningViewDTO> mapperScreening = new MapperScreeningView();
    private final FilmService filmService = AppContext.getInstance().getFilmService();
    private final IMapperService<Film, FilmDTO> mapperFilm = new MapperFilm();

    /**
     * Execute the view of screenings page command using the PRG pattern.
     *
     * @param req to get method, session and set all required attributes
     * @param resp to send response
     * @return path to redirect or forward by front-controller
     */
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getMethod().equals("POST")){
            return executePost(req, resp);
        } else {
            return executeGet(req);
        }
    }

    private String executePost(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();

        if (req.getParameter("btnApplySort") != null) {
            session.removeAttribute("orderScreening");
            session.removeAttribute("filterScreening");
        }
        setOrderToSession(getOrder(req, session), session);
        setFilterToSession(getFilter(req, session), session);

        CommandUtils.sendRedirectResponse(resp, Path.COMMAND_ADMIN_SCREENINGS);
        return Path.COMMAND_REDIRECT;
    }

    private String executeGet(HttpServletRequest req) {
        HttpSession session = req.getSession();

        String order = getOrder(req, session);
        long page = CommandUtils.getPage(req);
        String filter = getFilter(req, session);

        LocalDate currentDate = LocalDate.now();

        try {
            List<ScreeningView> screeningsList;
            long countPages;
            String orderBD = CommandUtils.getOrderBD(order);
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

            setOrderToSession(order, session);
            setFilterToSession(filter, session);
        } catch (DAOException | MapperException e) {
            log.error(e.getMessage());
        }

        return Path.ADMIN_MAIN;
    }

    private String getOrder(HttpServletRequest req, HttpSession session) {
        String order = req.getParameter("order");
        String orderSession = (String) session.getAttribute("orderScreening");
        order = orderSession != null ? orderSession : order;

        if(order == null || order.isEmpty()){
            order = "defaultScreening";
        }
        return order;
    }

    private void setOrderToSession(String order, HttpSession session){
        if(!order.equals("defaultScreening")){
            session.setAttribute("orderScreening", order);
        }
    }

    private String getFilter(HttpServletRequest req, HttpSession session) {
        String filter = req.getParameter("filter");
        String filterScreening = (String) session.getAttribute("filterScreening");
        filter = filterScreening != null ? filterScreening : filter;
        setFilterToSession(filter, session);
        return filter;
    }

    private void setFilterToSession(String filter, HttpSession session) {
        if(filter != null){
            session.setAttribute("filterScreening", "checked");
        }
    }

}
