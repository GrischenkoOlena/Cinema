package com.hryshchenko.cinema.controller.commands;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.constant.enums.StateScreening;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.dto.FilmDTO;
import com.hryshchenko.cinema.dto.ScreeningDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.FieldValidatorException;
import com.hryshchenko.cinema.model.dbservices.ScreeningService;
import com.hryshchenko.cinema.model.entity.Screening;
import com.hryshchenko.cinema.service.mapper.MapperScreening;
import static com.hryshchenko.cinema.util.DataValidator.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class AddScreeningCommand implements ICommand {
    private static final Logger log = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ScreeningService screeningService = AppContext.getInstance().getScreeningService();
        MapperScreening mapperService = new MapperScreening();

        String forward = Path.COMMAND_ADMIN_SCREENINGS;
        try {
            ScreeningDTO screeningDTO = getScreeningDTO(req);
            Screening screening = mapperService.getScreening(screeningDTO);
            if(!screeningService.createScreening(screening)){
                forward = Path.COMMAND_ERROR;
                req.getSession().setAttribute("error","error in create new session on level BD");
            }
        } catch (DAOException | FieldValidatorException e) {
            log.error(e.getMessage());
            req.getSession().setAttribute("errorAddScreening",e.getMessage());
        }

        sendRedirectResponse(resp, forward);
        log.info("new session was added");
        return Path.COMMAND_REDIRECT;
    }

    private void sendRedirectResponse(HttpServletResponse resp, String forward) {
        try {
            resp.sendRedirect(forward);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private ScreeningDTO getScreeningDTO(HttpServletRequest req) throws FieldValidatorException {
        long filmUpdate = Long.parseLong(req.getParameter("film"));

        LocalDate dateUpdate = LocalDate.parse(req.getParameter("date"));
        validateDateScreening(dateUpdate);

        LocalTime timeUpdate = LocalTime.parse(req.getParameter("time"));
        validateTimeScreening(timeUpdate);

        int stateUpdate = Integer.parseInt(req.getParameter("state"));

        ScreeningDTO screeningDTO = new ScreeningDTO();
        screeningDTO.setFilm(new FilmDTO.FilmDTOBuilder(filmUpdate).build());
        screeningDTO.setFilmDate(dateUpdate);
        screeningDTO.setTimeBegin(timeUpdate);
        screeningDTO.setState(StateScreening.getValueFromId(stateUpdate));
        return screeningDTO;
    }
}
