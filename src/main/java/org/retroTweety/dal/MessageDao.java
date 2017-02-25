package org.retroTweety.dal;

import org.retroTweety.service.domain.DomainMessage;
import org.retroTweety.service.domain.DomainUser;

import java.util.List;

/**
 * Created by alfrheim on 24/02/17.
 */
public interface MessageDao extends CRUD<DomainMessage, Long> {

    List<DomainMessage> getMessagesFrom(DomainUser user);
}
