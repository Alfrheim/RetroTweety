package org.retroTweety.service;

import org.retroTweety.service.domain.DomainUser;

/**
 * Created by alfrheim on 24/02/17.
 */
public interface UserService {
    DomainUser getUser(String userName);

    DomainUser createUser(String userName);

    void follow(DomainUser user, DomainUser userToFollow);
}

