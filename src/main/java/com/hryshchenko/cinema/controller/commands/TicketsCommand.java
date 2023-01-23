package com.hryshchenko.cinema.controller.commands;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.dto.TicketDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.entity.Ticket;
import com.hryshchenko.cinema.model.entity.User;
import com.hryshchenko.cinema.service.Pagination;
import com.hryshchenko.cinema.service.mapper.IMapperService;
import com.hryshchenko.cinema.service.mapper.MapperTicket;
import com.hryshchenko.cinema.util.OrderMapUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class TicketsCommand implements ICommand {
    private static final Logger log = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (req.getParameter("btnApplySort") != null) {
            session.removeAttribute("orderTickets");
        }

        String order = req.getParameter("order");
        String orderSession = (String) session.getAttribute("orderTickets");
        order = orderSession != null ? orderSession : order;

        if(order == null || order.isEmpty()){
            order = "defaultTicket";
        } else {
            session.setAttribute("orderTickets", order);
        }
        String orderBD = new OrderMapUtil().getOrderBD(order);

        long page;
        try {
            page = Long.parseLong(req.getParameter("page"));
        } catch (NumberFormatException e){
            page = 1;
        }

        Pagination ticketPagination = new Pagination(AppContext.getInstance());
        IMapperService<Ticket, TicketDTO> mapperService = new MapperTicket();
        try {
            List<Ticket> ticketList = ticketPagination.getTicketsPageByUser(orderBD, user.getId(), page);

            List<TicketDTO> ticketDTOList = mapperService.getListDTO(ticketList);
            req.setAttribute("tickets", ticketDTOList);

            long countPages = ticketPagination.getCountTicketPagesByUser(user.getId());
            req.setAttribute("countPages", countPages);
        } catch (DAOException | MapperException e) {
            log.error(e.getMessage());
        }

        return Path.USER_TICKETS;
    }
}
