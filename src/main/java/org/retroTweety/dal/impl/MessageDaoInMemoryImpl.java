package org.retroTweety.dal.impl;

import org.retroTweety.dal.MessageDao;
import org.retroTweety.dal.entity.MessageEntity;
import org.retroTweety.service.domain.DomainMessage;
import org.retroTweety.service.domain.DomainUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by alfrheim on 24/02/17.
 */
public class MessageDaoInMemoryImpl implements MessageDao {

    private final List<MessageEntity> db;

    public MessageDaoInMemoryImpl() {
        this.db =  Collections.synchronizedList(new ArrayList<>());
    }

    public MessageDaoInMemoryImpl(List<MessageEntity> db) {
        this.db = db;
    }

    @Override
    public DomainMessage update(DomainMessage entity) {
        // We will never update a message, we want the user to think before write ;)
        throw new UnsupportedOperationException();
    }

    @Override
    public DomainMessage create(DomainMessage entity) {
        db.add(MessageEntity.from(entity));
        return entity;
    }

    @Override
    public DomainMessage get(Long key) {
        // Our policy forbids to retrieve only one message
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Long key) {
        // Our CEO got drunk and deleted all messages. He said "Never again!", here is the result.
        throw new UnsupportedOperationException();
    }

    @Override
    public List<DomainMessage> getMessagesFrom(DomainUser user) {
        return db.stream()
                .filter(u -> u.getCreator().getUserName().equals(user.getName()))
                .map(MessageEntity::toDomainMessage)
                .collect(Collectors.toList());
    }
}
