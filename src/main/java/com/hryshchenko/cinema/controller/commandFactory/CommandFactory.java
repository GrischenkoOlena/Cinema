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

        commands.put("customers", new CustomersCommand());
        commands.put("screenings", new ScreeningsCommand());
        commands.put("films", new FilmsCommand());

        commands.put("tickets", new TicketsCommand());
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
        return commands.get(action);
    }
}
