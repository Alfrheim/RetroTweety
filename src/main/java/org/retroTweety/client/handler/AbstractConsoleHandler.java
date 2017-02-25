package org.retroTweety.client.handler;

import org.retroTweety.client.ConsoleUI;
import org.retroTweety.client.ConsoleHandler;
import org.retroTweety.controller.UserController;


/**
 * Created by alfrheim on 24/02/17.
 */
abstract class AbstractConsoleHandler implements ConsoleHandler {

    final UserController userController;
    final ConsoleUI consoleUI;

    AbstractConsoleHandler(UserController userController, ConsoleUI consoleUI) {
        this.userController = userController;
        this.consoleUI = consoleUI;
    }
}
