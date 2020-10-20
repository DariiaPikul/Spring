package com.spring.intro.dao;

import com.spring.intro.model.User;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    User create(User user);

    List<User> getAll();

    Optional<User> get(Long id);

    Optional<User> findByEmail(String email);
}
