package com.hryshchenko.cinema.controller.commands.user;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.constant.enums.UserRole;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.dto.ScreeningDTO;
import com.hryshchenko.cinema.dto.SeatDTO;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.service.mapper.MapperSeat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BasketCommand implements ICommand {
    private static final Logger log = LogManager.getLogger();
    private MapperSeat mapperSeat = new MapperSeat();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        UserRole userRole = (UserRole) session.getAttribute("userRole");

        ScreeningDTO screening = (ScreeningDTO) session.getAttribute("screening");
        int placeId = Integer.parseInt(req.getParameter("placeId"));

        SeatDTO seat = getSeatDTO(placeId);

        String response;
        if(userRole != null && userRole.equals(UserRole.CLIENT)){
            session.removeAttribute("errorUnregister");
            List<SeatDTO> seats = (ArrayList<SeatDTO>) session.getAttribute("seats");
            if (seats == null){
                seats = new ArrayList<>();
            }
            if(!seats.contains(seat)){
                seats.add(seat);
            }
            double cost = seats.stream().mapToDouble(temp -> temp.getCategory().getPrice()).sum();
            session.setAttribute("seats", seats);
            req.setAttribute("cost", cost);
            response = Path.TICKER_BASKET;
        } else {
            String message;
            if(userRole != null && userRole.equals(UserRole.ADMIN)){
                message = "You have to login as user to buy ticket";
            } else {
                message = "You have to register to buy ticket";
            }
            req.setAttribute("errorUnregister", message);
            session.setAttribute("errorUnregister",message);
            String forward = Path.COMMAND_FREE_SEATS + "&screeningId=" + screening.getId();
            response = Path.COMMAND_REDIRECT;
            sendRedirectRequest(resp, forward);
        }
        return response;
    }

    private void sendRedirectRequest(HttpServletResponse resp, String forward) {
        try {
            resp.sendRedirect(forward);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private SeatDTO getSeatDTO(int placeId) {
        SeatDTO seat = null;
        try {
           seat = mapperSeat.getSeatDTObyID(placeId);
        } catch (MapperException e) {
            log.error(e.getMessage());
        }
        return seat;
    }
}
