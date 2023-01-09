package com.hryshchenko.cinema.controller.commands;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.dto.ScreeningDTO;
import com.hryshchenko.cinema.model.entity.Screening;
import com.hryshchenko.cinema.service.Pagination;
import com.hryshchenko.cinema.service.mapper.IMapperService;
import com.hryshchenko.cinema.service.mapper.MapperScreening;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;

public class ScheduleCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String order = req.getParameter("order");
        String filter = req.getParameter("filter");

        long page;
        try {
            page = Long.parseLong(req.getParameter("page"));
        } catch (NumberFormatException e){
            page = 1;
        }

        LocalDate currentDate = LocalDate.now();

        Pagination screeningsPagination = new Pagination(AppContext.getInstance());
        IMapperService<Screening, ScreeningDTO> mapperService = new MapperScreening();
        try {
            List<Screening> screeningsList = screeningsPagination.getScreeningsPage(page);
            List<ScreeningDTO> screenings = mapperService.getListDTO(screeningsList);
            req.setAttribute("screenings", screenings);

            long countPages = screeningsPagination.getCountScreeningPages();
            req.setAttribute("countPages", countPages);
        } catch (DAOException | MapperException e) {
            e.printStackTrace();
        }
        return Path.USER_MAIN;
    }
}
