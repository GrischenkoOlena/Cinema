package com.hryshchenko.cinema.controller.commands.admin;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.controller.commands.CommandUtils;
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

/**
 *  Command for add new film. Available to the administrator.
 *
 *  @author Olena Hryshchenko
 */
public class AddFilmCommand implements ICommand {
    private static final Logger log = LogManager.getLogger();
    private final FilmService filmService;
    private final MapperFilm mapperService;

    public AddFilmCommand() {
        filmService = AppContext.getInstance().getFilmService();
        mapperService = new MapperFilm();
    }

    public AddFilmCommand(FilmService filmService, MapperFilm mapperService) {
        this.filmService = filmService;
        this.mapperService = mapperService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        String forward = Path.COMMAND_ADMIN_FILMS;
        try {
            FilmDTO filmDTO = getFilmDTO(req);
            Film film = mapperService.getFilm(filmDTO);
            if(!filmService.createFilm(film)){
                forward = Path.COMMAND_ERROR;
                req.getSession().setAttribute("error", "error in create new movie on level BD");
            }
        } catch (DAOException | MapperException | FieldValidatorException e) {
            log.error(e.getMessage());
            req.getSession().setAttribute("errorAddFilm", e.getMessage());
        }

        CommandUtils.sendRedirectResponse(resp, forward);
        log.info("new movie was added");
        return Path.COMMAND_REDIRECT;
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

        validateDuration(req.getParameter("duration"));
        int durationUpdate = Integer.parseInt(req.getParameter("duration"));

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
