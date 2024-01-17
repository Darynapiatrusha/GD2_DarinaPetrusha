package by.news.management.controller;

import java.util.HashMap;
import java.util.Map;

import by.news.management.controller.impl.ChangeLocalCommand;
import by.news.management.controller.impl.DeleteNewsCommand;
import by.news.management.controller.impl.EditNewsCommand;
import by.news.management.controller.impl.RegistrationCommand;
import by.news.management.controller.impl.SaveNewsCommand;
import by.news.management.controller.impl.ShowAuthPageCommand;
import by.news.management.controller.impl.ShowCreateNewsCommand;
import by.news.management.controller.impl.ShowErrorPage;
import by.news.management.controller.impl.ShowNewsEditCommand;
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
		commands.put(CommandName.CREATE_NEWS, new ShowCreateNewsCommand());
		commands.put(CommandName.EDIT_NEWS, new EditNewsCommand());
		commands.put(CommandName.DELETE_NEWS, new DeleteNewsCommand());
		commands.put(CommandName.SAVE_NEWS, new SaveNewsCommand());
		commands.put(CommandName.SHOW_NEWS_VIEW, new ShowNewsViewCommand());
		commands.put(CommandName.SHOW_NEWS_LIST, new ShowNewsListCommand());
		commands.put(CommandName.ERROR_REGISTRATION, new ShowRegistrationPageCommand());
		commands.put(CommandName.ERROR_AUTH, new ShowAuthPageCommand());
		commands.put(CommandName.SHOW_AUTH_PAGE, new ShowAuthPageCommand());
		commands.put(CommandName.SHOW_REGISTRATION_PAGE, new ShowRegistrationPageCommand());
		commands.put(CommandName.LOCAL, new ChangeLocalCommand());
		commands.put(CommandName.SHOW_EDIT_NEWS, new ShowNewsEditCommand());
		commands.put(CommandName.SHOW_ERROR, new ShowErrorPage());
	}

	public Command getCommand(String name)  {
		CommandName commandName = CommandName.valueOf(name.toUpperCase());
		return commands.get(commandName);
	}

	public static CommandProvider getInstance() {
		return instance;
	}
}