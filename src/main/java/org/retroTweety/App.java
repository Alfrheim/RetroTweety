package org.retroTweety;

import org.retroTweety.client.ConsoleUI;
import org.retroTweety.client.HandlerFactory;
import org.retroTweety.client.handler.ConsoleHandlerFactory;
import org.retroTweety.client.ui.RetroTweetyConsole;
import org.retroTweety.client.formatter.ConsoleFormatter;
import org.retroTweety.client.formatter.ConsolePrinter;
import org.retroTweety.controller.UserController;
import org.retroTweety.controller.impl.UserControllerImpl;
import org.retroTweety.dal.MessageDao;
import org.retroTweety.dal.impl.MessageDaoInMemoryImpl;
import org.retroTweety.dal.UserDao;
import org.retroTweety.dal.impl.UserDaoInMemoryImpl;
import org.retroTweety.service.PostService;
import org.retroTweety.service.UserService;
import org.retroTweety.service.impl.PostServiceImpl;
import org.retroTweety.service.impl.UserServiceImpl;

import java.util.Scanner;

/**
 * Created by alfrheim on 19/02/17.
 */
public class App {

    public static void main(String[] args) {
        UserDao userDao = new UserDaoInMemoryImpl();
        UserService userService = new UserServiceImpl(userDao);

        MessageDao messageDao = new MessageDaoInMemoryImpl();
        PostService postService = new PostServiceImpl(messageDao);

        UserController userController = new UserControllerImpl(userService, postService);
        ConsoleFormatter consoleFormatter = new ConsoleFormatter();
        ConsoleUI consoleUI = new RetroTweetyConsole(new ConsolePrinter(consoleFormatter), new Scanner(System.in));

        HandlerFactory handlerFactory = new ConsoleHandlerFactory(userController, consoleUI);
        RetroTweety rt = new RetroTweety(consoleUI, handlerFactory);

        rt.start();
    }



}
