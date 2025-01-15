package org.example.todolist.service;

import org.example.todolist.Enum.DegreeName;
import org.example.todolist.Enum.FacultyOrDepartment;
import org.example.todolist.model.User;
import org.example.todolist.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {
    @MockitoBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUserById_ShouldReturnUser_WhenUserExists() {
        //arrange
        User user = new User(1L, "testUser", "testmail@test.com", "password", "user", "Test User Name", "+84265921446", FacultyOrDepartment.Mathematics, true, DegreeName.Doctoral, "Biology", 4);
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        //act
        User returnedUser = userService.getUserById(1L);

        //assert
        assertNotNull(returnedUser);
        assertEquals("Test User Name", returnedUser.getFullName());
    }

    @Test
    void getUserById_ShouldReturnNull_WhenUserDoesNotExist() {
        //arrange
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        //act & assert
        assertThrows(RuntimeException.class, () -> userService.getUserById(1L));
    }

    @Test
    void deleteUserById_ShouldReturnTrue_WhenUserIsDeleted() {
        //arrange
        User user = new User(1L, "testUser", "testmail@test.com", "password", "user", "Test User Name", "+84265921446", FacultyOrDepartment.Mathematics, true, DegreeName.Doctoral, "Biology", 4);
        when(userRepository.existsById(1L)).thenReturn(true);
        doNothing().when(userRepository).deleteById(1L);
        //act
        boolean returnMessage = userService.deleteUser(user.getUserId());
        //assert
        Assertions.assertTrue(returnMessage);
    }

    @Test
    void deleteUserById_ShouldReturnFalse_WhenUserIdIsNotExisted() {
        //arrange
        User user = new User(1L, "testUser", "testmail@test.com", "password", "user", "Test User Name", "+84265921446", FacultyOrDepartment.Mathematics, true, DegreeName.Doctoral, "Biology", 4);
        when(userRepository.existsById(1L)).thenReturn(false);
        doNothing().when(userRepository).deleteById(1L);
        //act
        boolean returnMessage = userService.deleteUser(user.getUserId());
        //assert
        Assertions.assertFalse(returnMessage);
    }

    @Test
    void getAllUser_ShouldReturnPage_WhenUsersExisted() {
        //arrange
        User user1 = new User(1L, "testUser1", "testmail1@test.com", "password1", "user1", "Test User Name 1", "+842659214461", FacultyOrDepartment.Mathematics, true, DegreeName.Doctoral, "Biology", 4);
        User user2 = new User(2L, "testUser2", "testmail2@test.com", "password2", "user2", "Test User Name 2", "+842659214462", FacultyOrDepartment.Mathematics, true, DegreeName.Doctoral, "Biology", 4);
        User user3 = new User(3L, "testUser3", "testmail3@test.com", "password3", "user3", "Test User Name 3", "+842659214463", FacultyOrDepartment.Mathematics, true, DegreeName.Doctoral, "Biology", 4);

        Pageable pageable = PageRequest.of(0, 3, Sort.by("fullName").ascending());
        List<User> userList = List.of(user1, user2, user3);
        Page<User> userPage = new PageImpl<>(userList, pageable, userList.size());
        when(userRepository.findAllUser(any(Pageable.class))).thenReturn(userPage);
        //act
        Page<User> returnPage = userService.getAllUsers(0, 3);
        //assert
        Assertions.assertNotNull(returnPage);
        Assertions.assertEquals(userPage.getTotalElements(),returnPage.getTotalElements());
        Assertions.assertEquals(userPage.getContent(),returnPage.getContent());
    }

    @Test
    void filterUserByName_ShouldReturnUsers_WhenTheirFullNameAreMatched() {
        //arrange
        User user1 = new User(1L, "testUser1", "testmail1@test.com", "password1", "user1", "Test User Name 1", "+842659214461", FacultyOrDepartment.Mathematics, true, DegreeName.Doctoral, "Biology", 4);
        User user2 = new User(2L, "testUser2", "testmail2@test.com", "password2", "user2", "Test User Name 2", "+842659214462", FacultyOrDepartment.Mathematics, true, DegreeName.Doctoral, "Biology", 4);
        User user3 = new User(3L, "testUser3", "testmail3@test.com", "password3", "user3", "Test User Name 3", "+842659214463", FacultyOrDepartment.Mathematics, true, DegreeName.Doctoral, "Biology", 4);

        Pageable pageable = PageRequest.of(0, 3, Sort.by("fullName").ascending());
        when(userRepository.filterByFullName(any(String.class), any(Pageable.class))).thenReturn(new PageImpl<>(List.of(user1, user2, user3), pageable, 3));
        //act

        //assert
    }

}
