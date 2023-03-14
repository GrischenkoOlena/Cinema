package com.hryshchenko.cinema.controller.commands.admin;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.constant.enums.StateScreening;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.controller.commands.CommandUtils;
import com.hryshchenko.cinema.dto.FilmDTO;
import com.hryshchenko.cinema.dto.ScreeningDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.FieldValidatorException;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.dbservices.ScreeningService;
import com.hryshchenko.cinema.model.entity.Screening;
import com.hryshchenko.cinema.service.mapper.MapperScreening;
import static com.hryshchenko.cinema.util.DataValidator.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *  Command for add new screening. Available to the administrator.
 *
 *  @author Olena Hryshchenko
 */
public class AddScreeningCommand implements ICommand {
    private static final Logger log = LogManager.getLogger();

    private final ScreeningService screeningService = AppContext.getInstance().getScreeningService();
    private final MapperScreening mapperService = new MapperScreening();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        String forward = Path.COMMAND_ADMIN_SCREENINGS;
        try {
            ScreeningDTO screeningDTO = getScreeningDTO(req);
            checkTimeScreening(screeningDTO);
            Screening screening = mapperService.getScreening(screeningDTO);
            if(!screeningService.createScreening(screening)){
                forward = Path.COMMAND_ERROR;
                req.getSession().setAttribute("error","error in create new session on level BD");
            }
        } catch (DAOException | FieldValidatorException e) {
            log.error(e.getMessage());
            req.getSession().setAttribute("errorAddScreening",e.getMessage());
        }

        CommandUtils.sendRedirectResponse(resp, forward);
        log.info("new session was added");
        return Path.COMMAND_REDIRECT;
    }

    private ScreeningDTO getScreeningDTO(HttpServletRequest req) throws FieldValidatorException {
        long filmUpdate = Long.parseLong(req.getParameter("film"));

        String dateUpdate = req.getParameter("date");
        validateDateScreening(dateUpdate);
        LocalDate dateScreening = LocalDate.parse(dateUpdate);

        String timeUpdate = req.getParameter("time");
        validateTimeScreening(timeUpdate);
        LocalTime timeScreening = LocalTime.parse(timeUpdate);

        int stateUpdate = Integer.parseInt(req.getParameter("state"));

        ScreeningDTO screeningDTO = new ScreeningDTO();
        screeningDTO.setFilm(new FilmDTO.FilmDTOBuilder(filmUpdate).build());
        screeningDTO.setFilmDate(dateScreening);
        screeningDTO.setTimeBegin(timeScreening);
        screeningDTO.setState(StateScreening.getValueFromId(stateUpdate));
        return screeningDTO;
    }

    private void checkTimeScreening(ScreeningDTO screening) throws FieldValidatorException{
        LocalDate addedFilmDate = screening.getFilmDate();
        LocalTime addedTimeBegin = screening.getTimeBegin();
        int addedFilmDuration = screening.getFilm().getDuration();

        try {
            List<Screening> screeningsList = screeningService.getScreeningByDate(addedFilmDate);
            List<ScreeningDTO> screeningDTOList = mapperService.getListDTO(screeningsList);
            for(ScreeningDTO screeningDTO : screeningDTOList){
                LocalTime existTimeBegin = screeningDTO.getTimeBegin();
                LocalTime existTimeEnd = existTimeBegin.plusMinutes(screeningDTO.getFilm().getDuration());
                if(addedTimeBegin.isAfter(existTimeBegin) && addedTimeBegin.isBefore(existTimeEnd)){
                    throw new FieldValidatorException("You can't add new movie at busy time");
                }
                LocalTime addedTimeEnd = addedTimeBegin.plusMinutes(addedFilmDuration);
                if(addedTimeEnd.isBefore(existTimeEnd)){
                    throw new FieldValidatorException("You can't add new movie at busy time");
                }
            }
        } catch (DAOException | MapperException e) {
            log.error(e.getMessage());
        }
    }
}
