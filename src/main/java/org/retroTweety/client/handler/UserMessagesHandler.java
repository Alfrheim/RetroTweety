package org.retroTweety.client.handler;

import org.retroTweety.client.ConsoleUI;
import org.retroTweety.controller.UserController;

import java.util.Optional;

/**
 * Created by alfrheim on 25/02/17.
 */
class UserMessagesHandler extends AbstractConsoleHandler {
    UserMessagesHandler(UserController userController, ConsoleUI consoleUI) {
        super(userController, consoleUI);
    }

    @Override
    public void handle(String line) {
        Optional.ofNullable(userController.readUserMessages(line).getMessages())
                .ifPresent(u -> u.forEach(consoleUI::write));
    }
}
