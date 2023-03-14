package com.hryshchenko.cinema.controller.commands.admin;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.controller.commands.CommandUtils;
import com.hryshchenko.cinema.dto.UserDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.entity.User;
import com.hryshchenko.cinema.service.Pagination;
import com.hryshchenko.cinema.service.mapper.IMapperService;
import com.hryshchenko.cinema.service.mapper.MapperUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *  Command for view customers. Available to the administrator.
 *
 *  @author Olena Hryshchenko
 */
public class CustomersCommand implements ICommand {
    private static final Logger log = LogManager.getLogger();
    private final Pagination usersPagination = new Pagination(AppContext.getInstance());
    private final IMapperService<User, UserDTO> mapperService = new MapperUser();

    /**
     * Execute the view of customers page command using the PRG pattern.
     *
     * @param req to get method, session and set all required attributes
     * @param resp to send response
     * @return path to redirect or forward by front-controller
     */
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
        CommandUtils.sendRedirectResponse(resp, Path.COMMAND_ADMIN_CUSTOMERS);
        return Path.COMMAND_REDIRECT;
    }

    private String executeGet(HttpServletRequest req) {
        HttpSession session = req.getSession();

        String order = getOrder(req, session);
        String orderBD = CommandUtils.getOrderBD(order);
        long page = CommandUtils.getPage(req);

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

    private void setOrderToSession(String order, HttpSession session){
        if(!order.equals("defaultCustomer")){
            session.setAttribute("orderCustomers", order);
        }
    }

}
