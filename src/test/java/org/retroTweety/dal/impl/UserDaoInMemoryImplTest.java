package org.retroTweety.dal.impl;

import org.junit.Before;
import org.junit.Test;
import org.retroTweety.dal.UserDao;
import org.retroTweety.dal.entity.UserEntity;
import org.retroTweety.service.domain.DomainFollower;
import org.retroTweety.service.domain.DomainUser;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by alfrheim on 24/02/17.
 */
public class UserDaoInMemoryImplTest {

    private UserDao userDao;

    private Map<String, UserEntity> db;

    @Before
    public void setUp() throws Exception {
        db = new ConcurrentHashMap<>();
        userDao = new UserDaoInMemoryImpl(db);
    }

    @Test
    public void create_givenUser_userIsSaved() throws Exception {
        DomainUser user = new DomainUser("Pere", null);

        userDao.create(user);
        assertTrue(db.containsKey("Pere"));
    }

    @Test
    public void update_givenFollowers_updatesCorrectUser() throws Exception {
        DomainUser user = new DomainUser("Pere", null);

        userDao.update(user);
        user.getFollows().add(new DomainFollower("Lluis"));

        userDao.update(user);
        assertEquals(1, db.size());
        assertEquals(1, db.get("Pere").getFollows().size());
        Optional<UserEntity> followerSaved = db.get("Pere").getFollows().stream()
                .filter(u -> u.getUserName().equals("Lluis"))
                .findAny();
        assertEquals("Lluis", followerSaved.orElse(new UserEntity()).getUserName());
    }

    @Test
    public void get_givenUserWithFollower_returnsUserWithFollower() throws Exception {
        DomainUser user = new DomainUser("Pere", null);
        user.getFollows().add(new DomainFollower("Lluis"));

        userDao.update(user);

        DomainUser userSaved = userDao.get("Pere");
        assertEquals("Pere", userSaved.getName());
        DomainUser followerSaved = userSaved.getFollows().stream()
                .findAny()
                .orElse(new DomainFollower(null)).getUser();
        assertEquals("Lluis", followerSaved.getName());

    }

    @Test
    public void delete_givenAName_userIsDeleted() throws Exception {
        DomainUser user = new DomainUser("Pere", null);
        user.getFollows().add(new DomainFollower("Lluis"));

        userDao.update(user);

        assertEquals(1, db.size());
        userDao.delete(user.getName());
        assertEquals(0, db.size());
    }
}