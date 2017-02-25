package org.retroTweety.dal.impl;

import org.retroTweety.dal.UserDao;
import org.retroTweety.dal.entity.UserEntity;
import org.retroTweety.service.domain.DomainUser;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by alfrheim on 24/02/17.
 */
public class UserDaoInMemoryImpl implements UserDao {

    private final Map<String, UserEntity> db ;

    public UserDaoInMemoryImpl() {
        this.db = new ConcurrentHashMap<>();
    }

    public UserDaoInMemoryImpl(Map<String, UserEntity> db) {
        this.db = db;
    }

    @Override
    public DomainUser update(DomainUser entity) {
        db.put(entity.getName(), UserEntity.from(entity));
        return UserEntity.toDomainUser(db.get(entity.getName()));
    }

    @Override
    public DomainUser create(DomainUser entity) {
        return this.update(entity);
    }

    @Override
    public DomainUser get(String userName) {
        return UserEntity.toDomainUser(db.get(userName));
    }

    @Override
    public void delete(String key) {
        db.remove(key);
    }
}
