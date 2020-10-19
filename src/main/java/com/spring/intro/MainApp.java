package com.spring.intro;

import com.spring.intro.config.AppConfig;
import com.spring.intro.model.User;
import com.spring.intro.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    private static final AnnotationConfigApplicationContext appContext
            = new AnnotationConfigApplicationContext(AppConfig.class);
    private static final UserService userService = appContext.getBean(UserService.class);

    public static void main(String[] args) {
        userService.add(new User("alice@mail.com", "AlicePass"));
        userService.add(new User("bob@mail.com", "BobPass"));
        userService.listUsers().forEach(System.out::println);
    }
}
