package org.retroTweety.dal;

import org.retroTweety.service.domain.DomainUser;

/**
 * Created by alfrheim on 24/02/17.
 */
public interface CRUD<T, K> {

    T update(T entity);

    T create(T entity);

    T get(K key);
    void delete(K key);

}
