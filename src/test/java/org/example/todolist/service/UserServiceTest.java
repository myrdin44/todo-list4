package org.example.todolist.service;


import org.example.todolist.model.User;
import org.example.todolist.repository.UserRepository;
import org.example.todolist.security.CustomUserDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testFindByUserId_success() {
        //prepare
        Long userId = 1L;
        User mockUser = new User();
        mockUser.setUserId(userId);
        Mockito.when(userRepository.findByUserId(userId)).thenReturn(mockUser);

        //act
        CustomUserDetails customUserDetails = (CustomUserDetails) userService.findByUserId(userId);

        //assert
        Assertions.assertNotNull(customUserDetails);
        Assertions.assertEquals(mockUser.getUserId(), customUserDetails.getUserId());
    }

    @Test
    void testFindById_notFound() {
        Long userId = 1L;
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());

        //act & assert
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            userService.findByUserId(userId);
        });

        Assertions.assertEquals("User not found", exception.getMessage());
    }
}
