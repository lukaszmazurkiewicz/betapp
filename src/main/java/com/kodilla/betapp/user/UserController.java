package com.kodilla.betapp.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class UserController {
    private final UserMapper userMapper;
    private final UserService userService;

    @GetMapping
    public List<UserDto> getUsers() {
        log.info("Get all user called.");
        return userMapper.mapToUserDtoList(userService.getAllUsers());
    }

    @PostMapping
    Long addUser(@RequestBody UserDto userDto) {
        log.info("Add user called. UserDto [{}]", userDto);

        User user = userService.addUser(userMapper.mapToUser(userDto));

        return user.getId();
    }

    @PatchMapping("/{password}/{id}")
    UserDto changePassword(@PathVariable String password, @PathVariable long id) {
        log.info("Change password called.");

        return userMapper.mapToUserDto(userService.changePassword(password, id));
    }
}
