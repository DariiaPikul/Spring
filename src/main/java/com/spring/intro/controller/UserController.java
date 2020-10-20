package com.spring.intro.controller;

import com.spring.intro.dto.UserResponseDto;
import com.spring.intro.model.User;
import com.spring.intro.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/inject")
    public String injectData() {
        userService.add(new User("ashley@mail.com", "AshleyPass"));
        userService.add(new User("dariia@mail.com", "DariiaPass"));
        userService.add(new User("peter@mail.com", "PeterPass"));
        userService.add(new User("tyler@mail.com", "TylerPass"));
        return "Four users were injected successfully";
    }

    @GetMapping("/user_id={userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return mapToDto(userService.get(userId));
    }

    @GetMapping("/user_email={email}")
    public UserResponseDto get(@PathVariable String email) {
        return mapToDto(userService.findByEmail(email));
    }

    @GetMapping("/all")
    public List<UserResponseDto> getAll() {
        return userService.listUsers().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private UserResponseDto mapToDto(User user) {
        return new UserResponseDto(user.getId(), user.getEmail(), user.getPassword());
    }
}
