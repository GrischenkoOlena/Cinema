package com.hryshchenko.cinema.controller.commands.user;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.controller.commands.CommandUtils;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 *  Command for purchase all seats from basket. Available to the register user.
 *
 *  @author Olena Hryshchenko
 */
public class PurchaseCommand implements ICommand {
    private static final Logger log = LogManager.getLogger();

    private final UserService userService = AppContext.getInstance().getUserService();
    private final BusinessTicketService ticketService = new BusinessTicketService();
    private final MapperUser mapperUser = new MapperUser();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", CommandUtils.getLocale(session));

        User user = (User) session.getAttribute("user");
        session.removeAttribute("errorBuyTicket");

        ScreeningDTO screening = (ScreeningDTO) session.getAttribute("screening");
        List<SeatDTO> seats = (ArrayList<SeatDTO>) session.getAttribute("seats");

        TicketDTO ticketDTO = getTicketDTO(user, screening, seats);
        String forward = Path.COMMAND_BASKET;
        try {
            if(ticketService.buyTicket(ticketDTO)){
                Optional<User> userUpdate = userService.getUserByLogin(user.getLogin());
                userUpdate.ifPresent(value -> session.setAttribute("user", value));
                forward = Path.COMMAND_USER_TICKETS;
                session.removeAttribute("seats");
                log.info("Purchase ticket");
            }
        } catch (NotEnoughMoneyException e) {
            session.setAttribute("errorBuyTicket", bundle.getString("error.not.enough.money"));
        } catch (SeatHasSoldException e) {
            session.setAttribute("errorBuyTicket", bundle.getString("error.sold.place"));
        } catch (DAOException e) {
            log.error(e.getMessage());
        }
        CommandUtils.sendRedirectResponse(resp, forward);
        return Path.COMMAND_REDIRECT;
    }

    private TicketDTO getTicketDTO(User user, ScreeningDTO screening, List<SeatDTO> seats) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setUser(mapperUser.getDTO(user));
        ticketDTO.setScreening(screening);
        ticketDTO.setTicketCount(seats.size());
        ticketDTO.setSeats(seats);
        return ticketDTO;
    }
}
