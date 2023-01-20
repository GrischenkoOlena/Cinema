package com.hryshchenko.cinema.controller.commands;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.dto.SeatDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class DeleteBasketCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        List<SeatDTO> seats = (ArrayList<SeatDTO>) session.getAttribute("seats");
        int deleteSeatId = Integer.parseInt(req.getParameter("deleteSeatId"));

        seats.removeIf(seat -> seat.getId() == deleteSeatId);

        double cost = seats.stream().mapToDouble(temp -> temp.getCategory().getPrice()).sum();

        session.setAttribute("seats", seats);
        req.setAttribute("cost", cost);
        return Path.TICKER_BASKET;
    }
}
