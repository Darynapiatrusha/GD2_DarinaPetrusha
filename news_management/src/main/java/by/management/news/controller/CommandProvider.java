package by.management.news.controller;

import java.util.HashMap;
import java.util.Map;

import by.management.news.controller.impl.AuthorizationCommand;
import by.management.news.controller.impl.CreateNewsCommand;
import by.management.news.controller.impl.DeleteNewsCommand;
import by.management.news.controller.impl.EditNewsCommand;
import by.management.news.controller.impl.RegistrationCommand;

public final class CommandProvider {
	private static final CommandProvider instance = new CommandProvider();

	private Map<CommandName, Command> commands = new HashMap<>();

	private CommandProvider() {
		commands.put(CommandName.SIGN_UP, new RegistrationCommand());
		commands.put(CommandName.lOG_IN, new AuthorizationCommand());
		commands.put(CommandName.CREATE_NEWS, new CreateNewsCommand());
		commands.put(CommandName.EDIT_NEWS, new EditNewsCommand());
		commands.put(CommandName.DELETE_NEWS,new DeleteNewsCommand());
	}

	public Command getCommand(String name) {
		CommandName commandName = CommandName.valueOf(name.toUpperCase());
		return commands.get(commandName);
	}

	public static CommandProvider getInstance() {
		return instance;
	}
}