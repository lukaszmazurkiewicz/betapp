package com.kodilla.betapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInterface {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser() {
        return null;
    }

    @Override
    public void deleteUser() {

    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
