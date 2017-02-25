package org.retroTweety.controller;

import org.retroTweety.controller.out.UserMessages;
import org.retroTweety.controller.out.UserWall;

/**
 * Created by alfrheim on 21/02/17.
 */
public interface UserController {

    void post(String userName, String postMessage);

    UserWall getWall(String userName);

    void follow(String userName, String userToFollow);

    UserMessages readUserMessages(String userName);
}
