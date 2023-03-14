package com.hryshchenko.cinema.controller.commands.user;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.controller.commands.CommandUtils;
import com.hryshchenko.cinema.dto.ScreeningViewDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.entity.ScreeningView;
import com.hryshchenko.cinema.service.Pagination;
import com.hryshchenko.cinema.service.mapper.IMapperService;
import com.hryshchenko.cinema.service.mapper.MapperScreeningView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

/**
 *  Command for view the schedule of the movies available for viewing. Available to the register user.
 *
 *  @author Olena Hryshchenko
 */
public class ScheduleCommand implements ICommand {
    private static final Logger log = LogManager.getLogger();

    private Pagination screeningsPagination = new Pagination(AppContext.getInstance());
    private IMapperService<ScreeningView, ScreeningViewDTO> mapperScreening = new MapperScreeningView();

    /**
     * Execute the view of the schedule page command using the PRG pattern.
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
        }
        setOrderToSession(getOrder(req, session), session);
        CommandUtils.sendRedirectResponse(resp, Path.COMMAND_USER_SCHEDULE);
        return Path.COMMAND_REDIRECT;
    }

    private String executeGet(HttpServletRequest req) {
        HttpSession session = req.getSession();

        String order = getOrder(req, session);
        String orderBD = CommandUtils.getOrderBD(order);
        long page = CommandUtils.getPage(req);
        LocalDate currentDate = LocalDate.now();

        try {
            List<ScreeningView> screeningsList =
                    screeningsPagination.getFilterScreeningViewsPage(currentDate, orderBD, page);
            long countPages = screeningsPagination.getCountFilterScreeningViewPages(currentDate);
            List<ScreeningViewDTO> screenings = mapperScreening.getListDTO(screeningsList);

            req.setAttribute("screenings", screenings);
            req.setAttribute("countPages", countPages);
            setOrderToSession(order, session);
        } catch (DAOException | MapperException e) {
            log.error(e.getMessage());
        }
        return Path.USER_MAIN;
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
}
