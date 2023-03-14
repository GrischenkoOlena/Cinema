package com.hryshchenko.cinema.controller.commandFactory;

import com.hryshchenko.cinema.controller.commands.admin.*;
import com.hryshchenko.cinema.controller.commands.common.*;
import com.hryshchenko.cinema.controller.commands.user.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Main Command factory for Controller.
 *
 * @author Olena Hryshchenko
 */

public class CommandFactory {
    private static CommandFactory factory = new CommandFactory();
    private final static Map<String, ICommand> commands = new HashMap<>();
    static {
        //unregister user commands
        commands.put("main", new MainCommand());
        commands.put("enter", new EnterCommand());
        commands.put("login", new LoginCommand());
        commands.put("signUp", new SignUpCommand());
        commands.put("freeSeats", new FreeSeatCommand());

        //common commands
        commands.put("logout", new LogoutCommand());
        commands.put("i18n", new InternationalizationCommand());
        commands.put("profile", new ProfileCommand());
        commands.put("updateProfile", new UpdateProfileCommand());

        //admin commands
        commands.put("customers", new CustomersCommand());
        commands.put("screenings", new ScreeningsCommand());
        commands.put("films", new FilmsCommand());
        commands.put("attendance", new AttendanceCommand());
        commands.put("updateBalance", new UpdateBalanceCommand());
        commands.put("addFilm", new AddFilmCommand());
        commands.put("updateFilm", new UpdateFilmCommand());
        commands.put("addScreening", new AddScreeningCommand());
        commands.put("updateScreening", new UpdateScreeningCommand());

        //register user commands
        commands.put("tickets", new TicketsCommand());
        commands.put("schedule", new ScheduleCommand());
        commands.put("purchase", new PurchaseCommand());
        commands.put("basket", new BasketCommand());
        commands.put("deleteFromBasket", new DeleteBasketCommand());
        commands.put("turnTicket", new TurnTicketCommand());

        commands.put("empty", new EmptyCommand());
        commands.put("error", new ErrorCommand());
    }

    private CommandFactory() {}

    /**
     *  Constructor returns exist object or creates new (Singleton)
     */
    public static CommandFactory commandFactory() {
        if (factory == null) {
            factory = new CommandFactory();
        }
        return factory;
    }

    /**
     * @param req passed by controller
     * @return command executing required action
     */
    public ICommand getCommand(HttpServletRequest req){
        String action = req.getParameter("action");
        if (action == null || action.isEmpty()) {
            action = "empty";
        }
        return commands.get(action);
    }
}
