package org.retroTweety.service.domain;

/**
 * Created by alfrheim on 24/02/17.
 */
public class DomainFollower {


    private final DomainUser user;

    public DomainFollower(String name) {
        this.user = new DomainUser(name);
    }

    public static DomainFollower from(DomainUser u) {
        return new DomainFollower(u.getName());
    }

    public DomainUser getUser() {
        return user;
    }
}
