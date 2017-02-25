package org.retroTweety.service.domain;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Created by alfrheim on 21/02/17.
 */
public class DomainMessage {

    private final DomainUser domainUser;
    private final String message;
    private final ZonedDateTime created;

    public DomainMessage(DomainUser domainUser, String message) {
        this.domainUser = domainUser;
        this.message = message;
        this.created = ZonedDateTime.now(ZoneId.of("UTC"));
    }

    public DomainMessage(DomainUser domainUser, String message, ZonedDateTime created) {
        this.domainUser = domainUser;
        this.message = message;
        this.created = created;
    }

    public String getMessage() {
        return message;
    }

    public DomainUser getDomainUser() {
        return domainUser;
    }

    public ZonedDateTime getCreated() {
        return created;
    }
}
