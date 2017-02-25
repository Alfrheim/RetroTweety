package org.retroTweety.client.handler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.retroTweety.client.ConsoleHandler;
import org.retroTweety.client.ConsoleUI;
import org.retroTweety.client.HandlerFactory;
import org.retroTweety.controller.UserController;

import static org.junit.Assert.*;

/**
 * Created by alfrheim on 25/02/17.
 */
public class ConsoleHandlerFactoryTest {
    @Mock
    private ConsoleUI consoleUI;

    @Mock
    private UserController userController;

    private HandlerFactory handlerFactory;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.handlerFactory = new ConsoleHandlerFactory(userController, consoleUI);
    }

    @Test
    public void createHandler_givenPost_returnsPostHandler() throws Exception {
        ConsoleHandler result = handlerFactory.createHandler("pere -> I like black");

        assertTrue(result instanceof PostHandler);
    }

    @Test
    public void createHandler_givenFollow_returnsFollowHandler() throws Exception {
        ConsoleHandler result = handlerFactory.createHandler("pere follows rock");

        assertTrue(result instanceof FollowHandler);
    }
    @Test
    public void createHandler_givenPost_returnsWallHandler() throws Exception {
        ConsoleHandler result = handlerFactory.createHandler("pere wall");

        assertTrue(result instanceof WallHandler);
    }
    @Test
    public void createHandler_givenPost_returnsUserMessageHandler() throws Exception {
        ConsoleHandler result = handlerFactory.createHandler("pere");

        assertTrue(result instanceof UserMessagesHandler);
    }

}