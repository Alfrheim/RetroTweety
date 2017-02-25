package org.retroTweety.client.handler;

import org.retroTweety.client.ConsoleHandler;
import org.retroTweety.client.ConsoleUI;
import org.retroTweety.client.HandlerFactory;
import org.retroTweety.controller.UserController;

/**
 * Created by alfrheim on 25/02/17.
 */
public class ConsoleHandlerFactory implements HandlerFactory {

    private final UserController userController;
    private final ConsoleUI consoleUI;

    public ConsoleHandlerFactory(UserController userController, ConsoleUI consoleUI) {
        this.userController = userController;
        this.consoleUI = consoleUI;
    }

    @Override
    public ConsoleHandler createHandler(String commandLine) {
        ConsoleHandler result = null;
        if(commandLine.contains("->")) {
            result = new PostHandler(userController, consoleUI);

        } else if (commandLine.contains("wall")) {
            result = new WallHandler(userController, consoleUI);

        } else if (commandLine.contains("follows")) {
            result = new FollowHandler(userController, consoleUI);

        } else {
            result = new UserMessagesHandler(userController, consoleUI);
        }
        return result;
    }
}
