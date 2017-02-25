package org.retroTweety.client.formatter;

import org.retroTweety.controller.out.Message;

/**
 * Created by alfrheim on 24/02/17.
 */
public class ConsolePrinter implements TweetyPrinter {


    private ConsoleFormatter consoleFormatter;

    public ConsolePrinter(ConsoleFormatter consoleFormatter) {
        this.consoleFormatter = consoleFormatter;
    }

    @Override
    public void print(Message message) {
        System.out.println(consoleFormatter.format(message));
    }

    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
