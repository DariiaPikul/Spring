package com.spring.intro.service;

import com.spring.intro.model.User;
import java.util.List;

public interface UserService {
    void add(User user);

    List<User> listUsers();

    User get(Long id);
}
