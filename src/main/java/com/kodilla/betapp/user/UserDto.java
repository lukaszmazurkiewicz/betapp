package com.kodilla.betapp.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;
    private long walletId;
    private String login;
    private String password;

    public UserDto(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
