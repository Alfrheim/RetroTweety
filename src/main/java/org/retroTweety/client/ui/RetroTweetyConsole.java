package org.retroTweety.client.ui;

import org.retroTweety.client.ConsoleUI;
import org.retroTweety.client.formatter.TweetyPrinter;
import org.retroTweety.controller.out.Message;

import java.util.Scanner;

/**
 * Created by alfrheim on 24/02/17.
 */
public class RetroTweetyConsole implements ConsoleUI {

    private final TweetyPrinter tweetyPrinter;
    private final Scanner scanner;

    public RetroTweetyConsole(TweetyPrinter tweetyPrinter, Scanner scanner) {
        this.tweetyPrinter = tweetyPrinter;
        this.scanner = scanner;
    }

    @Override
    public String nextLine() {
        return scanner.nextLine().trim();
    }

    @Override
    public void printPrompt() {
        System.out.print("> ");
    }

    @Override
    public void write(Message message) {
        tweetyPrinter.print(message);
    }

    @Override
    public void write(String message) {
        tweetyPrinter.print(message);
    }
}
