package org.retroTweety.service.impl;

import org.retroTweety.dal.UserDao;
import org.retroTweety.service.UserService;
import org.retroTweety.service.domain.DomainUser;

/**
 * Created by alfrheim on 24/02/17.
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public DomainUser getUser(String userName) {
        return userDao.get(userName);
    }

    @Override
    public DomainUser createUser(String userName) {
        DomainUser user = new DomainUser(userName);

        return userDao.create(user);
    }

    @Override
    public void follow(DomainUser user, DomainUser userToFollow) {
        user.follow(userToFollow);
        userDao.update(user);
    }
}
