package com.kodilla.betapp.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTestSuite {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testAddUser() {
        //Given
        User user = new User("test", "testpass");
        User user2 = new User("test2", "testpass2");

        when(userRepository.save(any(User.class))).thenReturn(user);

        //When
        User testUser = userService.addUser(user2);

        //Then
        assertEquals(user.getId(), testUser.getId());
        assertEquals(user.getLogin(), testUser.getLogin());
        assertEquals(user.getPassword(), testUser.getPassword());
        assertEquals(user.getWallet(), testUser.getWallet());
        assertEquals(user.getEvents(), testUser.getEvents());
        assertEquals(user.getCoupons(), testUser.getCoupons());

    }

    @Test
    public void testGetUserById() {
        //Given
        User user = new User("test", "testpass");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        //When
        User testUser = userService.getUserById(2L);


        //Then
        assertEquals(user.getId(), testUser.getId());
        assertEquals(user.getLogin(), testUser.getLogin());
        assertEquals(user.getPassword(), testUser.getPassword());
        assertEquals(user.getWallet(), testUser.getWallet());
        assertEquals(user.getEvents(), testUser.getEvents());
        assertEquals(user.getCoupons(), testUser.getCoupons());
    }

    @Test(expected = UserNotFoundException.class)
    public void testGetUserByIdThrewException() {
        //Given
        User user = new User("test", "testpass");

        //when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        //When
        User testUser = userService.getUserById(2L);

        //Then
    }

    @Test
    public void testGetAllUsers() {
        //Given
        User user = new User("test", "testpass");
        User user2 = new User("test2", "testpass2");

        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user2);

        when(userRepository.findAll()).thenReturn(users);

        //When
        List<User> testUsersList = userService.getAllUsers();

        //Then
        assertEquals(2, testUsersList.size());
        assertTrue(testUsersList.contains(user));
        assertTrue(testUsersList.contains(user2));
    }

    @Test
    public void testChangePassword() {
        //Given
        User user = new User("test", "testpass");
        User user2 = new User("test2", "testpass2");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user2));
        when(userService.changePassword("lol",anyLong())).thenReturn(user);

        //When
        User testUser = userService.changePassword("lol", 2L);

        //Then
        assertEquals(user.getId(), testUser.getId());
        assertEquals(user.getLogin(), testUser.getLogin());
        assertEquals(user.getPassword(), testUser.getPassword());
        assertEquals(user.getWallet(), testUser.getWallet());
        assertEquals(user.getEvents(), testUser.getEvents());
        assertEquals(user.getCoupons(), testUser.getCoupons());
    }
}