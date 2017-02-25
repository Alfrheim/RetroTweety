package org.retroTweety.controller.out;

import java.util.List;

/**
 * Created by alfrheim on 24/02/17.
 */
public class UserWall {

    private final List<Message> messages;

    public UserWall(List<Message> messages) {
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return messages;
    }

}
