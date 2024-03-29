package com.hryshchenko.cinema.controller.commands.common;

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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *  Command for view session free seats. Available to every user.
 *
 *  @author Olena Hryshchenko
 */
public class FreeSeatCommand implements ICommand {
    private static final Logger log = LogManager.getLogger();
    private final ScreeningService screeningsServ = AppContext.getInstance().getScreeningService();
    private final SeatService seatServ = AppContext.getInstance().getSeatService();
    private final CategoryService categoryServ = AppContext.getInstance().getCategoryService();
    private final IMapperService<Screening, ScreeningDTO> mapperService = new MapperScreening();

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
            log.error(e.getMessage());
        }
        return Path.FREE_SEAT;
    }
}
