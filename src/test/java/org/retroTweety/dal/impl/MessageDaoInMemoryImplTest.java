package org.retroTweety.dal.impl;

import org.junit.Before;
import org.junit.Test;
import org.retroTweety.dal.MessageDao;
import org.retroTweety.dal.entity.MessageEntity;
import org.retroTweety.service.domain.DomainMessage;
import org.retroTweety.service.domain.DomainUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by alfrheim on 24/02/17.
 */
public class MessageDaoInMemoryImplTest {


    private List<MessageEntity> db;
    private MessageDao messageDao;

    @Before
    public void setUp() throws Exception {
        db =  Collections.synchronizedList(new ArrayList<>());
        messageDao = new MessageDaoInMemoryImpl(db);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void update() throws Exception {
        DomainUser user = new DomainUser("Pere");
        messageDao.update(new DomainMessage(user, "today is monday"));
    }

    @Test
    public void create() throws Exception {
        DomainMessage dmPere = createDomainMessageFrom("Pere");
        messageDao.create(dmPere);

        assertEquals(1, db.size());
        assertEquals(dmPere.getMessage(), db.get(0).getMessage());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void get() throws Exception {
        messageDao.get(0L);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void delete() throws Exception {
        messageDao.delete(0L);
    }

    @Test
    public void getMessagesFrom_givenTwoUsers_returnsMessagesFromOne() throws Exception {
        DomainMessage dmPere = createDomainMessageFrom("Pere");
        messageDao.create(dmPere);
        messageDao.create(dmPere);

        DomainMessage dmLluis = createDomainMessageFrom("Lluis");
        messageDao.create(dmLluis);

        List<DomainMessage> pereMessages = messageDao.getMessagesFrom(dmPere.getDomainUser());
        assertEquals(2, pereMessages.size());
        assertTrue(pereMessages.stream()
                .allMatch(m -> m.getMessage().equals(dmPere.getMessage())));
    }

    private DomainMessage createDomainMessageFrom(String name) {
        DomainUser user = new DomainUser(name);
        String message = "this Message is from " + name;
        return new DomainMessage(user, message);
    }

}