package com.hryshchenko.cinema.controller.commands;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.dto.FilmDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.dbservices.FilmService;
import com.hryshchenko.cinema.model.entity.Film;
import com.hryshchenko.cinema.service.mapper.MapperFilm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UpdateFilmCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        long filmId = Long.parseLong(req.getParameter("filmId"));
        String titleUpdate = req.getParameter("title");
        String directorUpdate = req.getParameter("director");
        String castUpdate = req.getParameter("cast");
        String descriptionUpdate = req.getParameter("description");
        int durationUpdate = Integer.parseInt(req.getParameter("duration"));
        FilmDTO filmDTO = new FilmDTO
                .FilmDTOBuilder(filmId)
                .title(titleUpdate)
                .director(directorUpdate)
                .cast(castUpdate)
                .description(descriptionUpdate)
                .duration(durationUpdate)
                .build();

        String forward = Path.COMMAND_ADMIN_FILMS;

        FilmService filmService = AppContext.getInstance().getFilmService();
        MapperFilm mapperService = new MapperFilm();
        try {
            Film film = mapperService.getFilm(filmDTO);
            if(!filmService.updateFilm(film)){
                forward = Path.ERROR;
                req.setAttribute("error","error in update film");
            }
        } catch (DAOException | MapperException e) {
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
