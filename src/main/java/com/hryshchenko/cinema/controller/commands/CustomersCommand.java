package com.hryshchenko.cinema.controller.commands;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CustomersCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        if (req.getParameter("btnApplySort") != null) {
            session.removeAttribute("orderCustomers");
        }

        String order = req.getParameter("order");
        String orderSession = (String) session.getAttribute("orderCustomers");
        order = orderSession != null ? orderSession : order;

        if(order == null || order.isEmpty()){
            order = "defaultCustomer";
        } else {
            session.setAttribute("orderCustomers", order);
        }
        String orderBD = new OrderMapUtil().getOrderBD(order);

        long page;
        try {
            page = Long.parseLong(req.getParameter("page"));
        } catch (NumberFormatException e){
            page = 1;
        }

        Pagination usersPagination = new Pagination(AppContext.getInstance());
        IMapperService<User, UserDTO> mapperService = new MapperUser();
        try {
            List<User> usersList = usersPagination.getUsersPage(orderBD, page);
            List<UserDTO> users = mapperService.getListDTO(usersList);
            req.setAttribute("users", users);

            long countPages = usersPagination.getCountUserPages();
            req.setAttribute("countPages", countPages);
        } catch (DAOException | MapperException e) {
            e.printStackTrace();
        }
        return Path.ADMIN_CUSTOMERS;
    }
}
