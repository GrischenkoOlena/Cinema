package com.hryshchenko.cinema.controller.commandFactory;

import com.hryshchenko.cinema.controller.commands.LoginCommand;
import com.hryshchenko.cinema.controller.commands.MainCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static CommandFactory factory = new CommandFactory();
    private final static Map<String, ICommand> commands = new HashMap<>();
    static {
        commands.put("login", new LoginCommand());
        commands.put("main", new MainCommand());
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
