package com.hryshchenko.cinema.controller.commands;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.dto.ScreeningDTO;
import com.hryshchenko.cinema.dto.SeatDTO;
import com.hryshchenko.cinema.dto.TicketDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.NotEnoughMoneyException;
import com.hryshchenko.cinema.exception.SeatHasSoldException;
import com.hryshchenko.cinema.model.dbservices.UserService;
import com.hryshchenko.cinema.model.entity.User;
import com.hryshchenko.cinema.service.business.BusinessTicketService;
import com.hryshchenko.cinema.service.mapper.MapperUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PurchaseCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        ScreeningDTO screening = (ScreeningDTO) session.getAttribute("screening");
        List<SeatDTO> seats = (ArrayList<SeatDTO>) session.getAttribute("seats");

        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setUser(new MapperUser().getDTO(user));
        ticketDTO.setScreening(screening);
        ticketDTO.setTicketCount(seats.size());
        ticketDTO.setSeats(seats);

        BusinessTicketService ticketService = new BusinessTicketService();
        try {
            if(ticketService.buyTicket(ticketDTO)){
                UserService userService = AppContext.getInstance().getUserService();
                Optional<User> userUpdate = userService.getUserByLogin(user.getLogin());
                userUpdate.ifPresent(value -> session.setAttribute("user", value));
            }
        } catch (NotEnoughMoneyException e) {
            session.setAttribute("errorBuyTicket", "You don't have enough money to purchase ticket");
            return Path.TICKER_BASKET;
        } catch (SeatHasSoldException e) {
            session.setAttribute("errorBuyTicket", "Unfortunately place has sold recently");
            return Path.TICKER_BASKET;
        } catch (DAOException e) {
            e.printStackTrace();
        }
        try {
            resp.sendRedirect(Path.COMMAND_USER_TICKETS);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Path.COMMAND_REDIRECT;
    }
}
