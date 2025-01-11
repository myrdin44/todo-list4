package org.example.todolist.service;

import org.example.todolist.model.User;
import org.example.todolist.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserService userService;
//
//    @Test
//    void getUserById_ShouldReturnUser_WhenUserExists() {
//        //arrange
//        User user = new User(1L, "testuser", "testmail@test.com", "password", "user", "Test User Name", "+84265921446", "default faculty", true, "Doctor", "Biology", 4);
//        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
//
//        //act
//        User returnedUser = userService.getUserById(1L);
//
//        //assert
//        assertNotNull(returnedUser);
//        assertEquals("Test User Name", returnedUser.getFullName());
//    }
//
//    @Test
//    void getUserById_ShouldReturnNull_WhenUserDoesNotExist() {
//        //arrange
//        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());
//
//        //act&assert
//        assertThrows(RuntimeException.class, () -> userService.getUserById(1L));
//
//    }
}
