package com.hryshchenko.cinema.controller.commands.admin;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.controller.commands.CommandUtils;
import com.hryshchenko.cinema.dto.AttendanceDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.service.Pagination;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *  Command for view attendance cinema. Available to the administrator.
 *
 *  @author Olena Hryshchenko
 */
public class AttendanceCommand implements ICommand {
    private static final Logger log = LogManager.getLogger();
    private final Pagination attendancePagination = new Pagination(AppContext.getInstance());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        long page = CommandUtils.getPage(req);

        try {
            List<AttendanceDTO> attendances = attendancePagination.getAttendancePage(page);
            req.setAttribute("attendances", attendances);

            long countPages = attendancePagination.getCountAttendancesPages();
            req.setAttribute("countPages", countPages);
        } catch (DAOException e) {
            log.error(e.getMessage());
        }
        return Path.ADMIN_ATTENDANCE;
    }
}
