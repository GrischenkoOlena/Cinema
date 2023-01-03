package com.hryshchenko.cinema.controller.commands;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.service.dto.ScreeningDTO;
import com.hryshchenko.cinema.model.service.dbservices.ScreeningService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;

public class MainCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String scheduleDate = req.getParameter("scheduleDate");
        LocalDate date = getScheduleDate(scheduleDate);

        ScreeningService screeningsServ = AppContext.getInstance().getScreeningService();
        try {
            List<ScreeningDTO> screenings = screeningsServ.getFullScreening(date);
            req.setAttribute("screenings", screenings);
        } catch (DAOException e) {
            e.printStackTrace();
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
