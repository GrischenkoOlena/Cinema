package com.hryshchenko.cinema.controller.commands.admin;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.dto.UserDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.entity.User;
import com.hryshchenko.cinema.service.Pagination;
import com.hryshchenko.cinema.service.mapper.IMapperService;
import com.hryshchenko.cinema.service.mapper.MapperUser;
import com.hryshchenko.cinema.util.OrderMapUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class CustomersCommand implements ICommand {
    private static final Logger log = LogManager.getLogger();
    private final Pagination usersPagination = new Pagination(AppContext.getInstance());
    private final IMapperService<User, UserDTO> mapperService = new MapperUser();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getMethod().equals("POST")){
            return executePost(req, resp);
        } else {
            return executeGet(req);
        }
    }

    private String executePost(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        if (req.getParameter("btnApplySort") != null) {
            session.removeAttribute("orderCustomers");
        }
        setOrderToSession(getOrder(req, session), session);
        try {
            resp.sendRedirect(Path.COMMAND_ADMIN_CUSTOMERS);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return Path.COMMAND_REDIRECT;
    }

    private String executeGet(HttpServletRequest req) {
        HttpSession session = req.getSession();

        String order = getOrder(req, session);
        String orderBD = getOrderBD(order);
        long page = getPage(req);

        try {
            List<User> usersList = usersPagination.getUsersPage(orderBD, page);
            List<UserDTO> users = mapperService.getListDTO(usersList);
            req.setAttribute("users", users);

            long countPages = usersPagination.getCountUserPages();
            req.setAttribute("countPages", countPages);

            setOrderToSession(order, session);
        } catch (DAOException | MapperException e) {
            log.error(e.getMessage());
        }
        return Path.ADMIN_CUSTOMERS;
    }

    private String getOrder(HttpServletRequest req, HttpSession session) {
        String order = req.getParameter("order");
        String orderSession = (String) session.getAttribute("orderCustomers");
        order = orderSession != null ? orderSession : order;

        if(order == null || order.isEmpty()){
            order = "defaultCustomer";
        }
        return order;
    }

    private String getOrderBD(String order) {
        return new OrderMapUtil().getOrderBD(order);
    }

    private void setOrderToSession(String order, HttpSession session){
        if(!order.equals("defaultCustomer")){
            session.setAttribute("orderCustomers", order);
        }
    }

    private long getPage(HttpServletRequest req) {
        long page;
        try {
            page = Long.parseLong(req.getParameter("page"));
        } catch (NumberFormatException e){
            page = 1;
        }
        return page;
    }
}
