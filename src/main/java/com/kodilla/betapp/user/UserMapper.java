package com.kodilla.betapp.user;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {
    User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getLogin(),
                userDto.getPassword()
        );
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getWallet().getId(),
                user.getLogin(),
                user.getPassword());
    }

    public List<UserDto> mapToUserDtoList(final List<User> users) {
        return users.stream()
                .map(u -> mapToUserDto(u))
                .collect(Collectors.toList());
    }
}
