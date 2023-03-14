package com.hryshchenko.cinema.controller.commands.user;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.controller.commands.CommandUtils;
import com.hryshchenko.cinema.dto.TicketDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.entity.Ticket;
import com.hryshchenko.cinema.model.entity.User;
import com.hryshchenko.cinema.service.Pagination;
import com.hryshchenko.cinema.service.mapper.IMapperService;
import com.hryshchenko.cinema.service.mapper.MapperTicket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *  Command for view user's tickets. Available to the register user.
 *
 *  @author Olena Hryshchenko
 */
public class TicketsCommand implements ICommand {
    private static final Logger log = LogManager.getLogger();

    private Pagination ticketPagination = new Pagination(AppContext.getInstance());
    private IMapperService<Ticket, TicketDTO> mapperService = new MapperTicket();

    /**
     * Execute the view of tickets page command using the PRG pattern.
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
            session.removeAttribute("orderTickets");
        }
        setOrderToSession(getOrder(req, session), session);
        CommandUtils.sendRedirectResponse(resp, Path.COMMAND_USER_TICKETS);
        return Path.COMMAND_REDIRECT;
    }

    private String executeGet(HttpServletRequest req) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        String order = getOrder(req, session);
        String orderBD = CommandUtils.getOrderBD(order);
        long page = CommandUtils.getPage(req);

        try {
            List<Ticket> ticketList = ticketPagination.getTicketsPageByUser(orderBD, user.getId(), page);
            List<TicketDTO> ticketDTOList = mapperService.getListDTO(ticketList);
            long countPages = ticketPagination.getCountTicketPagesByUser(user.getId());

            req.setAttribute("tickets", ticketDTOList);
            req.setAttribute("countPages", countPages);
            setOrderToSession(order, session);
        } catch (DAOException | MapperException e) {
            log.error(e.getMessage());
        }
        return Path.USER_TICKETS;
    }

    private String getOrder(HttpServletRequest req, HttpSession session) {
        String order = req.getParameter("order");
        String orderSession = (String) session.getAttribute("orderTickets");
        order = orderSession != null ? orderSession : order;

        if(order == null || order.isEmpty()){
            order = "defaultTicket";
        }
        return order;
    }

    private void setOrderToSession(String order, HttpSession session) {
        if(!order.equals("defaultTicket")){
            session.setAttribute("orderTickets", order);
        }
    }
}
