package org.retroTweety.client;

import org.retroTweety.controller.out.Message;

/**
 * Created by alfrheim on 25/02/17.
 */
public interface ConsoleUI {
    String nextLine();

    void printPrompt();

    void write(Message message);
    void write(String message);
}
