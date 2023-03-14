package com.hryshchenko.cinema.controller.commands.common;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.dto.ScreeningDTO;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.dbservices.ScreeningService;
import com.hryshchenko.cinema.model.entity.Screening;
import com.hryshchenko.cinema.service.mapper.IMapperService;
import com.hryshchenko.cinema.service.mapper.MapperScreening;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;

/**
 *  Command for view current sessions. Available to every user.
 *
 *  @author Olena Hryshchenko
 */
public class MainCommand implements ICommand {
    private static final Logger log = LogManager.getLogger();

    private ScreeningService screeningsServ = AppContext.getInstance().getScreeningService();
    private IMapperService<Screening, ScreeningDTO> mapperService = new MapperScreening();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String scheduleDate = req.getParameter("scheduleDate");
        LocalDate date = getScheduleDate(scheduleDate);

        try {
            List<Screening> screeningsList = screeningsServ.getScreeningByDate(date);
            List<ScreeningDTO> screenings = mapperService.getListDTO(screeningsList);
            req.setAttribute("screenings", screenings);
        } catch (DAOException | MapperException e) {
            log.error(e.getMessage());
        }
        return Path.MAIN;
    }

    private LocalDate getScheduleDate(String scheduleDate) {
        String[] dateParts = scheduleDate.split("/");
        int year = Integer.parseInt(dateParts[2]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[0]);
        return LocalDate.of(year, month, day);
    }
}
