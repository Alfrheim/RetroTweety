package org.retroTweety.dal.entity;

import org.retroTweety.service.domain.DomainFollower;
import org.retroTweety.service.domain.DomainUser;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by alfrheim on 24/02/17.
 */
public class UserEntity {
    private String userName;
    private Set<UserEntity> follows = new HashSet<>();

    public static UserEntity from(DomainUser entity) {
        Set<UserEntity> follows = entity.getFollows().stream()
                .map(DomainFollower::getUser)
                .map(UserEntity::from)
                .collect(Collectors.toSet());
        return new UserEntity()
                .setUserName(entity.getName())
                .addFollows(follows);
    }

    public static DomainUser toDomainUser(UserEntity userEntity) {
        DomainUser result = null;
        if(userEntity != null) {
            Set<DomainFollower> follows = userEntity.getFollows().stream()
                    .map(UserEntity::toFollowUser)
                    .collect(Collectors.toSet());
            result = new DomainUser(userEntity.getUserName(), follows);
        }

        return result;
    }

    private static DomainFollower toFollowUser(UserEntity userEntity) {
        return new DomainFollower(userEntity.getUserName());
    }


    public UserEntity setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserName() {
        return userName;
    }


    public Set<UserEntity> getFollows() {
        return follows;
    }

    public UserEntity addFollows(Set<UserEntity> follows) {
        this.follows.addAll(follows);
        return this;
    }

}
