package org.retroTweety.client.handler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.retroTweety.client.ConsoleHandler;
import org.retroTweety.controller.UserController;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by alfrheim on 25/02/17.
 */
public class FollowHandlerTest {

    @Mock
    private UserController userController;
    private ConsoleHandler followHandler;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        followHandler = new FollowHandler(userController, null);
    }

    @Test
    public void handle_givenInput_callsPost() throws Exception {
        followHandler.handle("marc follows pere");

        verify(userController, times(1)).follow("marc", "pere");
    }

}