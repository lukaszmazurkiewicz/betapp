package com.kodilla.betapp.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
    private long id;
    private String login;
    private String password;
}
