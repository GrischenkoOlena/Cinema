package com.hryshchenko.cinema.controller.commands.admin;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.controller.commands.CommandUtils;
import com.hryshchenko.cinema.dto.FilmDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.FieldValidatorException;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.dbservices.FilmService;
import com.hryshchenko.cinema.model.entity.Film;
import com.hryshchenko.cinema.service.mapper.MapperFilm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.hryshchenko.cinema.util.DataValidator.*;


public class UpdateFilmCommand implements ICommand {
    private static final Logger log = LogManager.getLogger();
    private final FilmService filmService = AppContext.getInstance().getFilmService();
    private final MapperFilm mapperService = new MapperFilm();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String forward = Path.COMMAND_ADMIN_FILMS;

        try {
            FilmDTO filmDTO = getFilmDTO(req);
            Film film = mapperService.getFilm(filmDTO);
            if(!filmService.updateFilm(film)){
                forward = Path.COMMAND_ERROR;
                req.setAttribute("error","error in update film");
            }
        } catch (DAOException | MapperException | FieldValidatorException e) {
            log.error(e.getMessage());
        }

        CommandUtils.sendRedirectResponse(resp, forward);
        return Path.COMMAND_REDIRECT;
    }

    private FilmDTO getFilmDTO(HttpServletRequest req) throws FieldValidatorException {
        long filmId = Long.parseLong(req.getParameter("filmId"));
        String titleUpdate = req.getParameter("title");
        validateTitle(titleUpdate);

        String directorUpdate = req.getParameter("director");
        validateDirector(directorUpdate);

        String castUpdate = req.getParameter("cast");
        validateCast(castUpdate);

        String descriptionUpdate = req.getParameter("description");

        validateDuration(req.getParameter("duration"));
        int durationUpdate = Integer.parseInt(req.getParameter("duration"));

        return new FilmDTO
                .FilmDTOBuilder(filmId)
                .title(titleUpdate)
                .director(directorUpdate)
                .cast(castUpdate)
                .description(descriptionUpdate)
                .duration(durationUpdate)
                .build();
    }
}
