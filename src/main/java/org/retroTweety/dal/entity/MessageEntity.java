package org.retroTweety.dal.entity;

import org.retroTweety.service.domain.DomainMessage;
import org.retroTweety.service.domain.DomainUser;

import java.time.ZonedDateTime;

/**
 * Created by alfrheim on 24/02/17.
 */
public class MessageEntity {

    private String message;
    private ZonedDateTime created;
    private UserEntity creator;

    public String getMessage() {
        return message;
    }

    public MessageEntity setMessage(String message) {
        this.message = message;
        return this;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public MessageEntity setCreated(ZonedDateTime created) {
        this.created = created;
        return this;
    }

    public UserEntity getCreator() {
        return creator;
    }

    public MessageEntity setCreator(UserEntity creator) {
        this.creator = creator;
        return this;
    }

    public static MessageEntity from(DomainMessage entity) {
        return new MessageEntity()
                .setCreated(entity.getCreated())
                .setCreator(UserEntity.from(entity.getDomainUser()))
                .setMessage(entity.getMessage());
    }

    public static  DomainMessage toDomainMessage(MessageEntity m) {
        return new DomainMessage(
                UserEntity.toDomainUser(m.getCreator()),
                m.getMessage(),
                m.getCreated());
    }
}
