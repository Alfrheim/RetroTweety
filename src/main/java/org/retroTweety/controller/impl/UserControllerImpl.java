package org.retroTweety.controller.impl;

import org.retroTweety.controller.UserController;
import org.retroTweety.controller.out.Message;
import org.retroTweety.controller.out.UserMessages;
import org.retroTweety.controller.out.UserWall;
import org.retroTweety.service.PostService;
import org.retroTweety.service.UserService;
import org.retroTweety.service.domain.DomainFollower;
import org.retroTweety.service.domain.DomainMessage;
import org.retroTweety.service.domain.DomainUser;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by alfrheim on 24/02/17.
 */
public class UserControllerImpl implements UserController {


    private UserService userService;
    private PostService postService;

    private static final  Comparator<DomainMessage> POSTS_DESC =
            (m1, m2) -> m2.getCreated().compareTo(m1.getCreated());

    public UserControllerImpl(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    public void post(String userName, String postMessage) {

        DomainUser user = this.getOrCreateUser(userName);

        postService.postMessage(user, postMessage);
    }

    public UserWall getWall(String userName) {
        DomainUser user = userService.getUser(userName);
        List<DomainMessage> posts = this.getAllWallPostsFromUser(user);

        return new UserWall(posts.stream()
                .map(Message::from)
                .collect(Collectors.toList()));
    }

    private List<DomainMessage> getAllWallPostsFromUser(DomainUser user) {
        List<DomainMessage> posts = user.getFollows().stream()
                .map(DomainFollower::getUser)
                .map(postService::getPosts)
                .collect(ArrayList::new, List::addAll, List::addAll);
        posts.addAll(postService.getPosts(user));
        posts.sort(POSTS_DESC);
        return posts;
    }


    public void follow(String userName, String userNameToFollow) {
        DomainUser user = this.getOrCreateUser(userName);
        DomainUser userToFollow = this.getOrCreateUser(userNameToFollow);

        userService.follow(user, userToFollow);
    }

    public UserMessages readUserMessages(String userName) {
        DomainUser user = this.getOrCreateUser(userName);
        List<DomainMessage> posts = postService.getPosts(user);
        posts.sort(POSTS_DESC);
        return new UserMessages(posts.stream()
                .map(Message::from)
                .collect(Collectors.toList()));
    }

    private DomainUser getOrCreateUser(final String userName) {
        return Optional.ofNullable(userService.getUser(userName))
                .orElseGet(() -> userService.createUser(userName));
    }
}
