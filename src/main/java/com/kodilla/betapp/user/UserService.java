package com.kodilla.betapp.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserServiceInterface {
    private UserRepository userRepository;

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found."));
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User changePassword(String password, long id) {
        User user = getUserById(id);
        user.setPassword(password);

        return userRepository.save(user);
    }

    @Override
    public BigDecimal checkBallanceOfAccount(long id) {
        User user = getUserById(id);
        BigDecimal ballance = user.getWallet().getAccountBalance();

        return ballance;
    }
}
