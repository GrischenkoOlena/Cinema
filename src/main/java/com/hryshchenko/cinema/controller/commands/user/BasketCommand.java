package com.hryshchenko.cinema.controller.commands.user;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.constant.enums.UserRole;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.dto.ScreeningDTO;
import com.hryshchenko.cinema.dto.SeatDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.dbservices.SeatService;
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
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        UserRole userRole = (UserRole) session.getAttribute("userRole");

        ScreeningDTO screening = (ScreeningDTO) session.getAttribute("screening");
        int placeId = Integer.parseInt(req.getParameter("placeId"));

        SeatDTO seat = getSeatDTO(placeId);

        String response = "";
        if(userRole != null){
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
            try {
                String message = "You have to register to buy ticket";
                req.setAttribute("errorUnregister", message);
                session.setAttribute("errorUnregister",message);
                long screeningId = screening.getId();
                String forward = Path.COMMAND_FREE_SEATS + "&screeningId=" + screeningId;
                resp.sendRedirect(forward);
                response = Path.COMMAND_REDIRECT;
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        return response;
    }

    private SeatDTO getSeatDTO(int placeId) {
        SeatService seatService = AppContext.getInstance().getSeatService();
        SeatDTO seat = new SeatDTO();
        try {
            seat = new MapperSeat().getDTO(seatService.getSeatById(placeId).get());
        } catch (MapperException | DAOException e) {
           log.error(e.getMessage());
        }
        return seat;
    }
}
