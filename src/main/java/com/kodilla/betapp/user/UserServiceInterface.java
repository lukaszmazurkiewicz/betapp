package com.kodilla.betapp.user;

import java.math.BigDecimal;
import java.util.List;

public interface UserServiceInterface {
    User addUser(User user);
    void deleteUser(long id);
    User changePassword(String password, long id);
    List<User> getAllUsers();
    User getUserById(long id);
    BigDecimal checkBallanceOfAccount(long id);
}
