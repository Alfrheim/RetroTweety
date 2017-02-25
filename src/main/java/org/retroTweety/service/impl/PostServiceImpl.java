package org.retroTweety.service.impl;

import org.retroTweety.dal.MessageDao;
import org.retroTweety.service.PostService;
import org.retroTweety.service.domain.DomainMessage;
import org.retroTweety.service.domain.DomainUser;

import java.util.List;

/**
 * Created by alfrheim on 24/02/17.
 */
public class PostServiceImpl implements PostService {
    private MessageDao messageDao;

    public PostServiceImpl(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Override
    public void postMessage(DomainUser domainUser, String postMessage) {
        DomainMessage message = new DomainMessage(domainUser, postMessage);
        messageDao.create(message);
    }

    @Override
    public List<DomainMessage> getPosts(DomainUser domainUser) {
        return messageDao.getMessagesFrom(domainUser);
    }
}
