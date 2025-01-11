package org.example.todolist.rest_controller;

import org.example.todolist.controller.UserController;
import org.example.todolist.model.User;
import org.example.todolist.security.JwtService;
import org.example.todolist.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockitoBean
//    private UserService userService;
//
//    @Mock
//    private JwtService jwtService;
//
//    @Test
//    void getUserById_ShouldReturnUser_WhenUserExists() throws Exception {
//        //arrang
//        User user = new User(1L, "testuser", "testmail@test.com", "password", "user", "Test User Name", "+84265921446", "default faculty", true, "Doctor", "Biology", 4);
//        Mockito.when(userService.getUserById(1L)).thenReturn(user);
//
//        //act&assert
//        mockMvc.perform(get("/operate/get-user?userId=1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.fullName").value("Test User Name"));
//    }
//
//    @Test
//    void getUserById_ShouldReturnUser_WhenUserDoesNotExist() throws Exception {
//        //arrange
//        Mockito.when(userService.getUserById(1L)).thenThrow(new RuntimeException("User not found!"));
//
//        //act&assert
//        mockMvc.perform(get("/operate/get-user?userId=1"))
//                .andExpect(status().isNotFound());
//    }
}
