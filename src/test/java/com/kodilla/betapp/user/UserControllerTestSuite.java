package com.kodilla.betapp.user;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private UserService userService;

    @Test
    public void testGetUser() throws Exception {
        //Given
        UserDto userDto = new UserDto("test", "testpass");
        UserDto userDto2 = new UserDto("test2", "testpass2");

        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(userDto);
        userDtos.add(userDto2);

        when(userMapper.mapToUserDtoList(userService.getAllUsers())).thenReturn(userDtos);
        mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].login", is("test")))
                .andExpect(jsonPath("$[0].password", is("testpass")));
    }

    @Test
    public void testAddUser() throws Exception {
        //Given
        User user = new User("test", "password");
        UserDto userDto = new UserDto("login", "test");

        when(userService.addUser(userMapper.mapToUser(userDto))).thenReturn(user);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);

        //When &  Then
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void testChangePassword() throws Exception {
        //Given
        String password = "password";
        long id = 2L;
        UserDto userDto = new UserDto(1L, 2L, "test", "password");

        when(userMapper.mapToUserDto(userService.changePassword(password, id))).thenReturn(userDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);

        //When & THen
        mockMvc.perform(patch("/users/password/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.walletId", is(2)))
                .andExpect(jsonPath("$.login", is("test")))
                .andExpect(jsonPath("$.password", is("password")));
    }

    @Test
    public void testDeleteUser() throws Exception {
        //Given

        //When & Then
        mockMvc.perform(delete("/users/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCheckBallanceOfAccount() throws Exception {
        //Given
        long id = 2L;

        when(userService.checkBallanceOfAccount(id)).thenReturn(BigDecimal.ONE);

        //When & Then
        mockMvc.perform(get("/users/ballance/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}