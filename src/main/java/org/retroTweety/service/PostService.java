package org.retroTweety.service;

import org.retroTweety.service.domain.DomainMessage;
import org.retroTweety.service.domain.DomainUser;

import java.util.List;

/**
 * Created by alfrheim on 24/02/17.
 */
public interface PostService {
    void postMessage(DomainUser domainUser, String postMessage);

    List<DomainMessage> getPosts(DomainUser domainUser);
}
