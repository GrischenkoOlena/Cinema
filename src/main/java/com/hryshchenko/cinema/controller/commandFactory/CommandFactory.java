package com.hryshchenko.cinema.controller.commandFactory;

import com.hryshchenko.cinema.controller.commands.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static CommandFactory factory = new CommandFactory();
    private final static Map<String, ICommand> commands = new HashMap<>();
    static {
        commands.put("main", new MainCommand());
        commands.put("enter", new EnterCommand());
        commands.put("login", new LoginCommand());
        commands.put("signUp", new SignUpCommand());
        commands.put("freeSeats", new FreeSeatCommand());

        commands.put("logout", new LogoutCommand());
        commands.put("i18n", new InternationalizationCommand());
        commands.put("profile", new ProfileCommand());

        commands.put("updateProfile", new UpdateProfileCommand());
        //commands.put("saveUpdate", new SaveUpdateProfileCommand());

        commands.put("customers", new CustomersCommand());
        commands.put("screenings", new ScreeningsCommand());
        commands.put("films", new FilmsCommand());
        commands.put("attendance", new AttendanceCommand());
        commands.put("updateBalance", new UpdateBalanceCommand());
        //commands.put("saveBalance", new SaveBalanceCommand());
        commands.put("addFilm", new AddFilmCommand());
        commands.put("addScreening", new AddScreeningCommand());

        commands.put("tickets", new TicketsCommand());
        commands.put("schedule", new ScheduleCommand());
        commands.put("purchase", new PurchaseCommand());

        commands.put("empty", new EmptyCommand());
    }

    private CommandFactory() {}

    public static CommandFactory commandFactory() {
        if (factory == null) {
            factory = new CommandFactory();
        }
        return factory;
    }

    public ICommand getCommand(HttpServletRequest req){
        String action = req.getParameter("action");
        if (action == null || action.isEmpty()) {
            action = "empty";
        }
        return commands.get(action);
    }
}
