package com.hryshchenko.cinema.controller.commands;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.dto.FilmDTO;
import com.hryshchenko.cinema.dto.GenreDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.FieldValidatorException;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.dbservices.FilmService;
import com.hryshchenko.cinema.model.entity.Film;
import com.hryshchenko.cinema.service.mapper.MapperFilm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.hryshchenko.cinema.util.DataValidator.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddFilmCommand implements ICommand {
    private static final Logger log = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        FilmDTO filmDTO;
        FilmService filmService = AppContext.getInstance().getFilmService();
        MapperFilm mapperService = new MapperFilm();

        String forward = Path.COMMAND_ADMIN_FILMS;
        try {
            filmDTO = getFilmDTO(req);
            Film film = mapperService.getFilm(filmDTO);
            if(!filmService.createFilm(film)){
                forward = Path.ERROR;
                req.setAttribute("error","error in create new movie");
            }
        } catch (DAOException | MapperException | FieldValidatorException e) {
            log.error(e.getMessage());
            req.setAttribute("errorAddFilm",e.getMessage());
        }

        sendRedirectRequest(resp, forward);
        return Path.COMMAND_REDIRECT;
    }

    private void sendRedirectRequest(HttpServletResponse resp, String forward) {
        try {
            resp.sendRedirect(forward);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private FilmDTO getFilmDTO(HttpServletRequest req) throws FieldValidatorException {
        String titleUpdate = req.getParameter("title");
        validateTitle(titleUpdate);

        String directorUpdate = req.getParameter("director");
        validateDirector(directorUpdate);

        String castUpdate = req.getParameter("cast");
        validateCast(castUpdate);

        String descriptionUpdate = req.getParameter("description");
        long genreUpdate = Long.parseLong(req.getParameter("genre"));

        int durationUpdate = Integer.parseInt(req.getParameter("duration"));
        validateDuration(durationUpdate);

        return new FilmDTO.FilmDTOBuilder(0)
                .title(titleUpdate)
                .director(directorUpdate)
                .cast(castUpdate)
                .description(descriptionUpdate)
                .genre(new GenreDTO(genreUpdate, ""))
                .duration(durationUpdate)
                .build();
    }
}
