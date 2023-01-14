package com.hryshchenko.cinema.controller.commands;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.constant.enums.StateScreening;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.dto.FilmDTO;
import com.hryshchenko.cinema.dto.GenreDTO;
import com.hryshchenko.cinema.dto.ScreeningDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.dbservices.FilmService;
import com.hryshchenko.cinema.model.dbservices.ScreeningService;
import com.hryshchenko.cinema.model.entity.Film;
import com.hryshchenko.cinema.model.entity.Screening;
import com.hryshchenko.cinema.service.mapper.MapperFilm;
import com.hryshchenko.cinema.service.mapper.MapperScreening;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class AddScreeningCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        long filmUpdate = Long.parseLong(req.getParameter("film"));
        LocalDate dateUpdate = LocalDate.parse(req.getParameter("date"));
        LocalTime timeUpdate = LocalTime.parse(req.getParameter("time"));
        int stateUpdate = Integer.parseInt(req.getParameter("state"));

        ScreeningDTO screeningDTO = new ScreeningDTO();
        screeningDTO.setFilm(new FilmDTO.FilmDTOBuilder(filmUpdate).build());
        screeningDTO.setFilmDate(dateUpdate);
        screeningDTO.setTimeBegin(timeUpdate);
        screeningDTO.setState(StateScreening.getValueFromId(stateUpdate));

        ScreeningService screeningService = AppContext.getInstance().getScreeningService();
        MapperScreening mapperService = new MapperScreening();

        String forward = Path.COMMAND_ADMIN_SCREENINGS;
        try {
            Screening screening = mapperService.getScreening(screeningDTO);
            if(!screeningService.createScreening(screening)){
                forward = Path.ERROR;
                req.setAttribute("error","error in create new movie");
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }

        try {
            resp.sendRedirect(forward);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Path.COMMAND_REDIRECT;
    }
}
