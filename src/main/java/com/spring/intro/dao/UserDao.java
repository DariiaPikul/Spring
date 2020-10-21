package com.spring.intro.dao;

import com.spring.intro.model.User;
import java.util.List;

public interface UserDao {
    User create(User user);

    List<User> getAll();

    User get(Long id);
}
