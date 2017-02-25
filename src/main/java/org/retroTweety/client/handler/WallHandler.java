package org.retroTweety.client.handler;

import org.retroTweety.client.ConsoleUI;
import org.retroTweety.controller.UserController;

import java.util.Optional;

/**
 * Created by alfrheim on 25/02/17.
 */
class WallHandler extends AbstractConsoleHandler {
    WallHandler(UserController userController, ConsoleUI consoleUI) {
        super(userController, consoleUI);
    }

    @Override
    public void handle(String line) {
        String[] split = line.split(" ");
        String userName = split[0].trim();
        Optional.ofNullable(userController.getWall(userName).getMessages())
                .ifPresent(m -> m.forEach(consoleUI::write));

    }
}
