package com.hryshchenko.cinema.controller.commands.user;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.constant.enums.UserRole;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.controller.commands.CommandUtils;
import com.hryshchenko.cinema.dto.ScreeningDTO;
import com.hryshchenko.cinema.dto.SeatDTO;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.service.mapper.MapperSeat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 *  Command for add new seat to user's basket. Available to the register user.
 *
 *  @author Olena Hryshchenko
 */
public class  BasketCommand implements ICommand {
    private static final Logger log = LogManager.getLogger();
    private final MapperSeat mapperSeat;

    public BasketCommand() {
        mapperSeat = new MapperSeat();
    }

    public BasketCommand(MapperSeat mapperSeat) {
        this.mapperSeat = mapperSeat;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        UserRole userRole = (UserRole) session.getAttribute("userRole");

        ScreeningDTO screening = (ScreeningDTO) session.getAttribute("screening");
        SeatDTO seat = getSeatDTO(req);

        String response;
        if(userRole != null && userRole.equals(UserRole.CLIENT)){
            session.removeAttribute("errorUnregister");
            List<SeatDTO> seats = addNextSeatToSession(session, seat);
            double cost = seats.stream().mapToDouble(temp -> temp.getCategory().getPrice()).sum();
            session.setAttribute("seats", seats);
            req.setAttribute("cost", cost);
            response = Path.TICKER_BASKET;
        } else {
            String message;
            ResourceBundle bundle = ResourceBundle.getBundle("messages", CommandUtils.getLocale(session));

            if(userRole != null && userRole.equals(UserRole.ADMIN)){
                message = bundle.getString("error.admin.buy.ticket");
            } else {
                message = bundle.getString("error.unregister.buy.ticket");
            }
            session.setAttribute("errorUnregister", message);
            String forward = Path.COMMAND_FREE_SEATS + "&screeningId=" + screening.getId();
            response = Path.COMMAND_REDIRECT;
            CommandUtils.sendRedirectResponse(resp, forward);
        }
        return response;
    }

    private List<SeatDTO> addNextSeatToSession(HttpSession session, SeatDTO seat) {
        @SuppressWarnings (value="unchecked")
        List<SeatDTO> seats = (ArrayList<SeatDTO>) session.getAttribute("seats");
        if (seats == null){
            seats = new ArrayList<>();
        }
        if(seat != null && !seats.contains(seat)){
            seats.add(seat);
        }
        return seats;
    }

    private SeatDTO getSeatDTO(HttpServletRequest req) {
        SeatDTO seat = null;
        try {
            int placeId = Integer.parseInt(req.getParameter("placeId"));
            seat = mapperSeat.getSeatDTObyID(placeId);
        } catch (NumberFormatException | MapperException e){
            log.error(e.getMessage());
        }
        return seat;
    }
}
