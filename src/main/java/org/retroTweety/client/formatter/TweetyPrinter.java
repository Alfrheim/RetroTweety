package org.retroTweety.client.formatter;

import org.retroTweety.controller.out.Message;

/**
 * Created by alfrheim on 25/02/17.
 */
public interface TweetyPrinter {
    void print(Message message);

    void print(String message);
}
