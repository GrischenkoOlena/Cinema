package com.hryshchenko.cinema.controller.commands.user;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.dto.ScreeningViewDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.entity.ScreeningView;
import com.hryshchenko.cinema.service.Pagination;
import com.hryshchenko.cinema.service.mapper.IMapperService;
import com.hryshchenko.cinema.service.mapper.MapperScreeningView;
import com.hryshchenko.cinema.util.OrderMapUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

public class ScheduleCommand implements ICommand {
    private static final Logger log = LogManager.getLogger();

    private Pagination screeningsPagination = new Pagination(AppContext.getInstance());
    private IMapperService<ScreeningView, ScreeningViewDTO> mapperScreening = new MapperScreeningView();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();

        if (req.getParameter("btnApplySort") != null) {
            session.removeAttribute("orderScreening");
        }

        String orderBD = getOrderBD(req, session);
        long page = getPage(req);
        LocalDate currentDate = LocalDate.now();

        try {
            List<ScreeningView> screeningsList;
            long countPages;
            screeningsList = screeningsPagination.getFilterScreeningViewsPage(currentDate, orderBD, page);
            countPages = screeningsPagination.getCountFilterScreeningViewPages(currentDate);
            List<ScreeningViewDTO> screenings = mapperScreening.getListDTO(screeningsList);

            req.setAttribute("screenings", screenings);
            req.setAttribute("countPages", countPages);
        } catch (DAOException | MapperException e) {
            log.error(e.getMessage());
        }
        return Path.USER_MAIN;
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

    private String getOrderBD(HttpServletRequest req, HttpSession session) {
        String order = req.getParameter("order");
        String orderSession = (String) session.getAttribute("orderScreening");
        order = orderSession != null ? orderSession : order;

        if(order == null || order.isEmpty()){
            order = "defaultScreening";
        } else {
            session.setAttribute("orderScreening", order);
        }
        return new OrderMapUtil().getOrderBD(order);
    }
}
