package org.retroTweety.client.handler;

import org.retroTweety.client.ConsoleUI;
import org.retroTweety.controller.UserController;

/**
 * Created by alfrheim on 24/02/17.
 */
class FollowHandler extends AbstractConsoleHandler {

    FollowHandler(UserController userController, ConsoleUI consoleUI) {
        super(userController, consoleUI);
    }

    @Override
    public void handle(String line) {
            String[] split = line.split("follows");
            String userName = split[0].trim();
            String userToFollow = split[1].trim();
            userController.follow(userName, userToFollow);

    }
}
