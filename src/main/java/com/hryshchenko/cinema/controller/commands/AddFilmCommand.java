package com.hryshchenko.cinema.controller.commands;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.dto.FilmDTO;
import com.hryshchenko.cinema.dto.GenreDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.dbservices.FilmService;
import com.hryshchenko.cinema.model.entity.Film;
import com.hryshchenko.cinema.service.mapper.MapperFilm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddFilmCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String titleUpdate = req.getParameter("title");
        String directorUpdate = req.getParameter("director");
        String castUpdate = req.getParameter("cast");
        String descriptionUpdate = req.getParameter("description");
        long genreUpdate = Long.parseLong(req.getParameter("genre"));
        int durationUpdate = Integer.parseInt(req.getParameter("duration"));

        FilmDTO filmDTO = new FilmDTO
                .FilmDTOBuilder(0)
                .title(titleUpdate)
                .director(directorUpdate)
                .cast(castUpdate)
                .description(descriptionUpdate)
                .genre(new GenreDTO(genreUpdate, ""))
                .duration(durationUpdate)
                .build();

        FilmService filmService = AppContext.getInstance().getFilmService();
        MapperFilm mapperService = new MapperFilm();

        String forward = Path.COMMAND_ADMIN_FILMS;
        try {
            Film film = mapperService.getFilm(filmDTO);
            if(!filmService.createFilm(film)){
                forward = Path.ERROR;
                req.setAttribute("error","error in create new movie");
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
