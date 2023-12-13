package by.news.management.controller;

import java.util.HashMap;
import java.util.Map;

import by.news.management.controller.impl.CancelCommand;
import by.news.management.controller.impl.CreateNewsCommand;
import by.news.management.controller.impl.DeleteNewsCommand;
import by.news.management.controller.impl.EditNewsCommand;
import by.news.management.controller.impl.ErrorAuthCommand;
import by.news.management.controller.impl.ErrorRegistrationCommand;
import by.news.management.controller.impl.RegistrationCommand;
import by.news.management.controller.impl.SaveNewsCommand;
import by.news.management.controller.impl.ShowAuthPageCommand;
import by.news.management.controller.impl.ShowIndexPageCommand;
import by.news.management.controller.impl.ShowNewsListCommand;
import by.news.management.controller.impl.ShowNewsViewCommand;
import by.news.management.controller.impl.ShowRegistrationPageCommand;
import by.news.management.controller.impl.SignInCommand;
import by.news.management.controller.impl.SignOutCommand;

public final class CommandProvider {
	private static final CommandProvider instance = new CommandProvider();

	private Map<CommandName, Command> commands = new HashMap<>();

	private CommandProvider() {
		commands.put(CommandName.REGISTRATION, new RegistrationCommand());
		commands.put(CommandName.SIGN_IN, new SignInCommand());
		commands.put(CommandName.SIGN_OUT, new SignOutCommand());
		commands.put(CommandName.CREATE_NEWS, new CreateNewsCommand());
		commands.put(CommandName.EDIT_NEWS, new EditNewsCommand());
		commands.put(CommandName.DELETE_NEWS,new DeleteNewsCommand());
		commands.put(CommandName.SAVE_NEWS, new SaveNewsCommand());
		commands.put(CommandName.SHOW_NEWS_VIEW, new ShowNewsViewCommand());
		commands.put(CommandName.SHOW_NEWS_LIST, new ShowNewsListCommand());
		commands.put(CommandName.CANCEL, new CancelCommand());
		commands.put(CommandName.ERROR_REGISTRATION, new ErrorRegistrationCommand());
		commands.put(CommandName.ERROR_AUTH, new ErrorAuthCommand());
		commands.put(CommandName.SHOW_INDEX_PAGE, new ShowIndexPageCommand());
		commands.put(CommandName.SHOW_AUTH_PAGE, new ShowAuthPageCommand());
		commands.put(CommandName.SHOW_REGISTRATION_PAGE, new ShowRegistrationPageCommand());
	}

	public Command getCommand(String name) {
		CommandName commandName = CommandName.valueOf(name.toUpperCase());
		return commands.get(commandName);
	}

	public static CommandProvider getInstance() {
		return instance;
	}
}