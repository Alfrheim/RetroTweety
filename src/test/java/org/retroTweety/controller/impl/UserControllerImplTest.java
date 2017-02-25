package org.retroTweety.controller.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.retroTweety.controller.UserController;
import org.retroTweety.controller.out.UserMessages;
import org.retroTweety.controller.out.UserWall;
import org.retroTweety.service.PostService;
import org.retroTweety.service.UserService;
import org.retroTweety.service.domain.DomainFollower;
import org.retroTweety.service.domain.DomainMessage;
import org.retroTweety.service.domain.DomainUser;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by alfrheim on 24/02/17.
 */
public class UserControllerImplTest {

    private static final String PERE = "pere";
    private static final String LLUIS = "lluis";
    @Mock
    private UserService userService;

    @Mock
    private PostService postService;

    private UserController userController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userController = new UserControllerImpl(userService, postService);
    }

    @Test
    public void post_givenExistingUser_returnsUser() throws Exception {
        when(userService.getUser(PERE)).thenReturn(new DomainUser(PERE));

        userController.post(PERE, "Something interesting happened to me");

        verify(userService, times(1)).getUser(PERE);

    }

    @Test
    public void post_givenExistingUser_verifyCreateIsNotCalled() throws Exception {
        when(userService.getUser(PERE)).thenReturn(new DomainUser(PERE));

        userController.post(PERE, "Something interesting happened to me");

        verify(userService, never()).createUser(any());
    }

    @Test
    public void getWall_givenUser_returnsHisPosts() throws Exception {
        DomainUser pere = new DomainUser(PERE);
        when(userService.getUser(PERE)).thenReturn(pere);

        when(postService.getPosts(any())).thenReturn(this.createDomainmessages(4, pere));

        UserWall wall = userController.getWall(PERE);

        assertEquals(4, wall.getMessages().size());
    }

    @Test
    public void getWall_givenUser_returnsHisPostsAndFollowers() throws Exception {
        DomainUser lluis = new DomainUser(LLUIS);
        when(userService.getUser(LLUIS)).thenReturn(lluis);

        DomainUser pere = new DomainUser(PERE);
        pere.getFollows().add(new DomainFollower(LLUIS));
        when(userService.getUser(PERE)).thenReturn(pere);

        when(postService.getPosts(any())).thenReturn(this.createDomainmessages(4, pere));

        UserWall wall = userController.getWall(PERE);

        assertEquals(8, wall.getMessages().size());
    }

    @Test
    public void follow_givenTwoUsers_verifyOneIsCreated() throws Exception {
        DomainUser lluis = new DomainUser(LLUIS);
        when(userService.getUser(LLUIS)).thenReturn(null);
        when(userService.createUser(LLUIS)).thenReturn(lluis);

        DomainUser pere = new DomainUser(PERE);
        when(userService.getUser(PERE)).thenReturn(pere);

        userController.follow(PERE, LLUIS);

        verify(userService, times(1)).follow(any(), any());
        verify(userService, times(2)).getUser(any());
        verify(userService, times(1)).createUser(any());

    }

    @Test
    public void readUserMessages_givenUser_returnsMessages() throws Exception {
        DomainUser pere = new DomainUser(PERE);
        when(userService.getUser(PERE)).thenReturn(pere);

        when(postService.getPosts(any())).thenReturn(this.createDomainmessages(4, pere));

        UserMessages userMessages = userController.readUserMessages(PERE);

        assertEquals(4, userMessages.getMessages().size());
    }

    private List<DomainMessage> createDomainmessages(int i, DomainUser domainUser) {
        return IntStream.range(0, i)
                .mapToObj(id ->
                        new DomainMessage(domainUser,
                                this.getPostMessage(domainUser, id)))
                .collect(Collectors.toList());
    }

    private String getPostMessage(DomainUser domainUser, int id) {
        return String.format("this is post %d from user %s", id, domainUser.getName());
    }

}