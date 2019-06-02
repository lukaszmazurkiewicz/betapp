package com.kodilla.betapp.user;

public interface UserServiceInterface {
    User addUser(User user);
    void deleteUser(User user);
    User changePassword(String password, long id);
}
