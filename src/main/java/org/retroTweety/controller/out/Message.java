package org.retroTweety.controller.out;

import org.retroTweety.service.domain.DomainMessage;

import java.time.ZonedDateTime;

/**
 * Created by alfrheim on 24/02/17.
 */
public class Message {

    private User user;
    private String message;
    private ZonedDateTime created;


    public Message(User user, String message, ZonedDateTime created) {
        this.user = user;
        this.message = message;
        this.created = created;
    }

    public static Message from(DomainMessage d) {
        return new Message(User.from(d.getDomainUser()), d.getMessage(), d.getCreated());
    }

    public User getUser() {
        return user;
    }

    public Message setUser(User user) {
        this.user = user;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Message setMessage(String message) {
        this.message = message;
        return this;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public Message setCreated(ZonedDateTime created) {
        this.created = created;
        return this;
    }
}
