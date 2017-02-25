package org.retroTweety.service.domain;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by alfrheim on 21/02/17.
 */
public class DomainUser {
    private String name;
    private Set<DomainFollower> follows;

    public DomainUser(String name) {
        this(name, new HashSet<>());
    }

    public DomainUser(String name, Set<DomainFollower> follows) {
        this.name = name;
        this.follows= Optional.ofNullable(follows).orElse(new HashSet<>());
    }

    public String getName() {
        return name;
    }

    public DomainUser setName(String name) {
        this.name = name;
        return this;
    }

    public DomainUser follow(DomainUser userToFollow) {
        this.getFollows().add(DomainFollower.from(userToFollow));
        return this;
    }

    public Set<DomainFollower> getFollows() {
        return follows;
    }


}
