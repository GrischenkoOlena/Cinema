package com.hryshchenko.cinema.controller.commands;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.dto.ScreeningDTO;
import com.hryshchenko.cinema.model.dto.SeatDTO;
import com.hryshchenko.cinema.model.entity.Category;
import com.hryshchenko.cinema.model.entity.Screening;
import com.hryshchenko.cinema.model.service.CategoryService;
import com.hryshchenko.cinema.model.service.ScreeningService;
import com.hryshchenko.cinema.model.service.SeatService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FreeSeatCommand implements ICommand {
    private final ScreeningService screeningsServ = AppContext.getInstance().getScreeningService();
    private final SeatService seatServ = AppContext.getInstance().getSeatService();
    private final CategoryService categoryServ = AppContext.getInstance().getCategoryService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        long screeningId = Long.parseLong((req.getParameter("screening")));

        try {
            Screening screening = screeningsServ.getScreeningById(screeningId);
            req.setAttribute("screening", ScreeningDTO.build(screening));

            SeatDTO[][] seats = seatServ.getFullFreeSeats(screening);
            req.setAttribute("seats", seats);

            List<Category> categories = categoryServ.getAllCategory();
            req.setAttribute("categories", categories);

        } catch (DAOException e) {
            e.printStackTrace();
        }
        return Path.FREE_SEAT;
    }
}
