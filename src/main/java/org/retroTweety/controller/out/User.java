package org.retroTweety.controller.out;

import org.retroTweety.service.domain.DomainUser;

/**
 * Created by alfrheim on 24/02/17.
 */
public class User {
    private final String userfName;

    public User(String userfName) {
        this.userfName = userfName;
    }

    public String getUserfName() {
        return userfName;
    }

    public static User from(DomainUser d) {
        return new User(d.getName());
    }
}
