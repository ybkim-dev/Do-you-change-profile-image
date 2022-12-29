package com.project.dci.domain.repository;

import com.project.dci.domain.User;

import java.util.Optional;

public interface UserRepository {
    void upsertUser(User user);
    Optional<User> findUserByUUID(String uuid);
    void saveUser(User user);
}
