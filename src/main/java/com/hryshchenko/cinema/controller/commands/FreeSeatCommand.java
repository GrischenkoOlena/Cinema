package com.hryshchenko.cinema.controller.commands;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.dto.ScreeningDTO;
import com.hryshchenko.cinema.dto.SeatDTO;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.entity.Category;
import com.hryshchenko.cinema.model.entity.Screening;
import com.hryshchenko.cinema.model.dbservices.CategoryService;
import com.hryshchenko.cinema.model.dbservices.ScreeningService;
import com.hryshchenko.cinema.model.dbservices.SeatService;
import com.hryshchenko.cinema.service.mapper.IMapperService;
import com.hryshchenko.cinema.service.mapper.MapperScreening;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FreeSeatCommand implements ICommand {
    private final ScreeningService screeningsServ = AppContext.getInstance().getScreeningService();
    private final SeatService seatServ = AppContext.getInstance().getSeatService();
    private final CategoryService categoryServ = AppContext.getInstance().getCategoryService();
    IMapperService<Screening, ScreeningDTO> mapperService = new MapperScreening();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        long screeningId = Long.parseLong((req.getParameter("screeningId")));

        try {
            if(screeningsServ.getScreeningById(screeningId).isPresent()){
                Screening screening = screeningsServ.getScreeningById(screeningId).get();
                ScreeningDTO screeningDTO = mapperService.getDTO(screening);
                req.setAttribute("screening", screeningDTO);
                session.setAttribute("screening", screeningDTO);

                SeatDTO[][] seats = seatServ.getFullFreeSeats(screening);
                req.setAttribute("seats", seats);
            }
            List<Category> categories = categoryServ.getAllCategory();
            req.setAttribute("categories", categories);

        } catch (DAOException | MapperException e) {
            e.printStackTrace();
        }
        return Path.FREE_SEAT;
    }
}
