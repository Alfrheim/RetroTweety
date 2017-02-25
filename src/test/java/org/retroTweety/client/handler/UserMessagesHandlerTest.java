package org.retroTweety.client.handler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.retroTweety.client.ConsoleHandler;
import org.retroTweety.client.ConsoleUI;
import org.retroTweety.client.formatter.ConsoleFormatter;
import org.retroTweety.client.formatter.ConsolePrinter;
import org.retroTweety.client.ui.RetroTweetyConsole;
import org.retroTweety.controller.UserController;
import org.retroTweety.controller.out.Message;
import org.retroTweety.controller.out.User;
import org.retroTweety.controller.out.UserMessages;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by alfrheim on 25/02/17.
 */
public class UserMessagesHandlerTest {
    private ConsoleUI consoleUI;

    @Mock
    private UserController userController;
    private ConsoleHandler userMessagesHandler;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        consoleUI = new RetroTweetyConsole(
                new ConsolePrinter(new ConsoleFormatter()), new Scanner(System.in));
        userMessagesHandler = new UserMessagesHandler(userController, consoleUI);
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void handle_givenPosts_printPosts() throws Exception {
        User pere = new User("pere");
        List<Message> messages = this.createMessages(2, pere);
        UserMessages userMessages = new UserMessages(messages);
        when(userController.readUserMessages("pere")).thenReturn(userMessages);

        userMessagesHandler.handle("pere");

        String result = "pere - this is post 0 from user pere (0 second ago)\n" +
                "pere - this is post 1 from user pere (0 second ago)\n";
        assertEquals(result,  outContent.toString());
    }

    @Test
    public void handle_givenEmptyPosts_printsEmpty() throws Exception {
        User pere = new User("pere");
        UserMessages userMessages = new UserMessages(new ArrayList<>());
        when(userController.readUserMessages("pere")).thenReturn(userMessages);

        userMessagesHandler.handle("pere");

        assertEquals("",  outContent.toString());
    }



    private List<Message> createMessages(int i, User user) {
        return IntStream.range(0, i)
                .mapToObj(id ->
                        new Message(user,
                                this.getPostMessage(user, id),
                                ZonedDateTime.now(ZoneId.of("UTC"))))
                .collect(Collectors.toList());
    }

    private String getPostMessage(User user, int id) {
        return String.format("this is post %d from user %s", id, user.getUserfName());
    }

}