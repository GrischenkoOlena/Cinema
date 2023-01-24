package com.hryshchenko.cinema.controller.commands.admin;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.dto.AttendanceDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.service.Pagination;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AttendanceCommand implements ICommand {
    private static final Logger log = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        long page;
        try {
            page = Long.parseLong(req.getParameter("page"));
        } catch (NumberFormatException e){
            page = 1;
        }

        Pagination attendancePagination = new Pagination(AppContext.getInstance());
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
