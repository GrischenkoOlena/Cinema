package com.hryshchenko.cinema.controller.commands.user;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.dto.SeatDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeleteBasketCommand implements ICommand {
    private static final Logger log = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        List<SeatDTO> seats = (ArrayList<SeatDTO>) session.getAttribute("seats");
        int deleteSeatId = Integer.parseInt(req.getParameter("deleteSeatId"));

        seats.removeIf(seat -> seat.getId() == deleteSeatId);

        double cost = seats.stream().mapToDouble(temp -> temp.getCategory().getPrice()).sum();

        session.setAttribute("seats", seats);
        req.setAttribute("cost", cost);
        try {
            resp.sendRedirect(Path.COMMAND_BASKET);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return Path.COMMAND_REDIRECT;
    }
}
