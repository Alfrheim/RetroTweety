package org.retroTweety;

import org.retroTweety.client.ConsoleHandler;
import org.retroTweety.client.ConsoleUI;
import org.retroTweety.client.HandlerFactory;

/**
 * Created by alfrheim on 19/02/17.
 */
public class RetroTweety {

    private boolean running = false;

    private ConsoleUI consoleUI;
    private HandlerFactory handlerFactory;

    public RetroTweety(ConsoleUI consoleUI, HandlerFactory handlerFactory) {
        this.consoleUI = consoleUI;
        this.handlerFactory = handlerFactory;
        running = true;
    }

    void start() {
        this.printHelp();
        while(running) {
            consoleUI.printPrompt();
            String inputCommandLine = consoleUI.nextLine();
            this.execute(inputCommandLine);
        }
    }

    private void printHelp() {
        String help =
                "This are the commands available:\n" +
                        "        posting:   <user name> -> <message>\n" +
                        "        reading:   <user name>\n" +
                        "        following: <user name> follows <another user>\n" +
                        "        wall:      <user name> wall\n" +
                        "        quit:      quit\n" +
                        "        help:      help";
        consoleUI.write(help);
    }

    private void execute(String inputCommandLine) {

        if(inputCommandLine.equals("quit")) {
            running = false;
        }

        if(inputCommandLine.equals("help")) {
           printHelp();
        }

        ConsoleHandler handler = handlerFactory.createHandler(inputCommandLine);
        handler.handle(inputCommandLine);
    }

}
