package com.hryshchenko.cinema.controller.commands.user;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.controller.commands.CommandUtils;
import com.hryshchenko.cinema.dto.TicketDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.dbservices.UserService;
import com.hryshchenko.cinema.model.entity.User;
import com.hryshchenko.cinema.service.business.BusinessTicketService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TurnTicketCommand implements ICommand {
    private static final Logger log = LogManager.getLogger();
    private BusinessTicketService ticketService = new BusinessTicketService();
    private UserService userService = AppContext.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();

        long ticketId = Long.parseLong(req.getParameter("ticketId"));
        User user = (User) session.getAttribute("user");
        List<TicketDTO> tickets = (List<TicketDTO>) session.getAttribute("tickets");

        TicketDTO ticket = tickets.stream()
                .filter((temp)-> temp.getId()==ticketId)
                .collect(Collectors.toList())
                .get(0);
        if(ticketService.turnTicket(ticket)){
            try {
                Optional<User> userUpdate = userService.getUserByLogin(user.getLogin());
                userUpdate.ifPresent(value -> session.setAttribute("user", value));
            } catch (DAOException e) {
                log.error(e.getMessage());
            }
        }
        CommandUtils.sendRedirectResponse(resp, Path.COMMAND_USER_TICKETS);
        log.info("Ticket is returned");
        return Path.COMMAND_REDIRECT;
    }
}
