package org.retroTweety.client.handler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.retroTweety.client.ConsoleHandler;
import org.retroTweety.client.ConsoleUI;
import org.retroTweety.controller.UserController;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by alfrheim on 25/02/17.
 */
public class PostHandlerTest {

    @Mock
    private UserController userController;
    private ConsoleHandler postHandler;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        postHandler = new PostHandler(userController, null);
    }

    @Test
    public void handle_givenInput_callsPost() throws Exception {
        postHandler.handle("marc -> apples!");

        verify(userController, times(1)).post("marc", "apples!");
    }

}