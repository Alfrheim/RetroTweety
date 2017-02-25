package org.retroTweety.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.retroTweety.dal.UserDao;
import org.retroTweety.service.UserService;
import org.retroTweety.service.domain.DomainUser;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by alfrheim on 24/02/17.
 */
public class UserServiceImplTest {
    @Mock
    private UserDao userDao;
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userDao);
    }

    @Test
    public void getUser_givenAName_returnsUser() throws Exception {
        DomainUser mockedUser = new DomainUser("Pere");
        when(userDao.get("Pere")).thenReturn(mockedUser);

        DomainUser user = userService.getUser("Pere");
        assertEquals(mockedUser.getName(), user.getName());
        assertEquals(mockedUser.getFollows(), user.getFollows());
    }

    @Test
    public void createUser_givenName_callsCreate() throws Exception {
        userService.createUser("Pere");
        verify(userDao, times(1)).create(any());
    }

    @Test
    public void follow_givenUserToFollow_verifyHasFollow() throws Exception {
        DomainUser pere = new DomainUser("Pere");
        DomainUser lluis = new DomainUser("Lluis");

        userService.follow(pere, lluis);
        assertEquals(1, pere.getFollows().size());
        verify(userDao, times(1)).update(any());
    }

}