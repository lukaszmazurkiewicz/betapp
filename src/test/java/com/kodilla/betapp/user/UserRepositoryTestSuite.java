package com.kodilla.betapp.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTestSuite {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Test
    public void testFindAll() {
        //Given
        long sizeOfDatabaseBeforeTest = userRepository.count();

        User user = new User("test", "testpass");
        User user2 = new User("test2", "testpass2");

        userRepository.save(user);
        userRepository.save(user2);

        //When
        List<User> users = userRepository.findAll();

        //Then
        assertEquals(2, users.size() - sizeOfDatabaseBeforeTest);
        assertTrue(users.contains(user));
        assertTrue(users.contains(user2));
    }

    @Transactional
    @Test
    public void testFindById() {
        //Given
        User user = new User("test", "testpass");

        userRepository.save(user);

        //When
        Optional<User> testUser = userRepository.findById(user.getId());

        //Then
        assertTrue(testUser.isPresent());
        assertEquals(Optional.of(user), testUser);
        assertEquals("test", testUser.get().getLogin());
        assertEquals("testpass", testUser.get().getPassword());
    }

    @Transactional
    @Test
    public void testSave() {
        //Given
        long sizeOfDatabaseBeforeTest = userRepository.count();

        User user = new User("test", "testpass");
        User user2 = new User("test2", "testpass2");

        //When
        userRepository.save(user);
        userRepository.save(user2);

        //Then
        assertEquals(2L, userRepository.count() - sizeOfDatabaseBeforeTest);
    }

    @Transactional
    @Test
    public void testDeleteById() {
        //Given
        long sizeOfDatabaseBeforeTest = userRepository.count();

        User user = new User("test", "testpass");

        userRepository.save(user);

        //When
        userRepository.deleteById(user.getId());

        //Then
        assertEquals(0, userRepository.count() - sizeOfDatabaseBeforeTest);
    }
}