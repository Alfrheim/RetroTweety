package org.retroTweety.controller.out;

import java.util.List;

/**
 * Created by alfrheim on 24/02/17.
 */
public class UserMessages {
    private final List<Message> messages;

    public UserMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
