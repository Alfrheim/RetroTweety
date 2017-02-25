package org.retroTweety.client;

import org.retroTweety.client.ConsoleHandler;

/**
 * Created by alfrheim on 25/02/17.
 */
public interface HandlerFactory {
    ConsoleHandler createHandler(String commandLine);
}
