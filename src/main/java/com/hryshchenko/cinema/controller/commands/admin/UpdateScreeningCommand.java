package com.hryshchenko.cinema.controller.commands.admin;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.constant.enums.StateScreening;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.controller.commands.CommandUtils;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.dbservices.ScreeningService;
import com.hryshchenko.cinema.model.entity.Screening;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  Command for update screening. Available to the administrator.
 *
 *  @author Olena Hryshchenko
 */
public class UpdateScreeningCommand implements ICommand {
    private static final Logger log = LogManager.getLogger();
    private final ScreeningService screeningServ = AppContext.getInstance().getScreeningService();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String forward = Path.COMMAND_ADMIN_SCREENINGS;

        long screeningId = Long.parseLong(req.getParameter("screeningId"));
        String state = req.getParameter("state");
        try{
            Screening screening = screeningServ.getScreeningById(screeningId).orElseThrow();
            changeState(state, screening);
            if(!screeningServ.updateScreening(screening)){
                forward = Path.COMMAND_ERROR;
                req.setAttribute("error","error in update session");
            }
        } catch (DAOException e) {
            log.error(e.getMessage());
        }

        CommandUtils.sendRedirectResponse(resp, forward);
        log.info("State session is changed");
        return Path.COMMAND_REDIRECT;
    }

    private void changeState(String state, Screening screening) {
        if(state.equalsIgnoreCase(StateScreening.ACTIVE.name())){
            screening.setStateId(StateScreening.CANCELED.getId());
        } else if (state.equalsIgnoreCase(StateScreening.CANCELED.name())) {
            screening.setStateId(StateScreening.ACTIVE.getId());
        }
    }
}
