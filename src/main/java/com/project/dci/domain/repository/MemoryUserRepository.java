package com.project.dci.domain.repository;

import com.project.dci.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryUserRepository implements UserRepository{

    private static final Map<String, User> store = new ConcurrentHashMap<>();

    @Override
    public void upsertUser(User user) {
        store.put(user.getUuid(), user);
    }

    @Override
    public Optional<User> findUserByUUID(String uuid) {
        if(store.containsKey(uuid)) return Optional.of(store.get(uuid));
        else return Optional.empty();
    }

    @Override
    public void saveUser(User user) {
        store.put(user.getUuid(), user);
    }
}
