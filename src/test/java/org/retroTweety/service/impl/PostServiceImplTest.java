package org.retroTweety.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.retroTweety.dal.MessageDao;
import org.retroTweety.service.PostService;
import org.retroTweety.service.domain.DomainUser;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by alfrheim on 24/02/17.
 */
public class PostServiceImplTest {

    @Mock
    private MessageDao messageDao;
    private PostService postService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        postService = new PostServiceImpl(messageDao);
    }

    @Test
    public void postMessage_givenAUserAndMessage_verifyCreate() throws Exception {
        DomainUser pere = new DomainUser("Pere");

        postService.postMessage(pere, "First post of the day!");

        verify(messageDao, times(1)).create(any());
    }

    @Test
    public void getPosts_givenUser_verifyCallsGetMessagesFrom() throws Exception {
        DomainUser pere = new DomainUser("Pere");

        postService.getPosts(pere);

        verify(messageDao, times(1)).getMessagesFrom(pere);
    }

}