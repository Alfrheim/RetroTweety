package org.retroTweety.client.handler;

import org.retroTweety.client.ConsoleUI;
import org.retroTweety.controller.UserController;

/**
 * Created by alfrheim on 24/02/17.
 */
class PostHandler extends AbstractConsoleHandler {

    PostHandler(UserController userController, ConsoleUI consoleUI) {
        super(userController, consoleUI);
    }

    @Override
    public void handle(String line) {
        String[] split = line.split("->");
        String userName = split[0].trim();
        String postMessage = split[1].trim();
        userController.post(userName, postMessage);

    }
}
