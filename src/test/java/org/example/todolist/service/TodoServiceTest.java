package org.example.todolist.service;

import org.example.todolist.Enum.DegreeName;
import org.example.todolist.Enum.FacultyOrDepartment;
import org.example.todolist.model.Todo;
import org.example.todolist.model.User;
import org.example.todolist.repository.TodoRepository;
import org.example.todolist.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.ZonedDateTime;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TodoServiceTest {
    @MockitoBean
    private TodoRepository todoRepository;
    @MockitoBean
    private UserRepository userRepository;

    @Autowired
    private TodoService todoService;

    @Test
    void addTask_ShouldReturnTodo_WhenTaskIsAdded() {
        //arrange
        User user = new User(1L, "testUser", "testmail@test.com", "password", "user",
                "Test User Name", "+84265921446",
                FacultyOrDepartment.Mathematics, true, DegreeName.Doctoral,
                "Biology", 4);
        Todo todoTest = new Todo(4L, "Test Title", "This is test description!", ZonedDateTime.now(), false, true, user);

        when(userRepository.findByUserId(1L)).thenReturn(user);

        when(todoRepository.save(todoTest)).thenReturn(todoTest);

        //act
        Todo returnTodo = todoService.addTask(todoTest, user.getUserId());

        //assert
        Assertions.assertNotNull(returnTodo);
        Assertions.assertEquals("Test Title", returnTodo.getTitle());

    }

    @Test
    void deleteTask_ShouldReturnTrue_WhenTaskIsDeleted() {
        //arrange
        User user = new User(1L, "testUser", "testmail@test.com", "password", "user",
                "Test User Name", "+84265921446",
                FacultyOrDepartment.Mathematics, true, DegreeName.Doctoral,
                "Biology", 4);
        Todo todoTest = new Todo(4L, "Test Title", "This is test description!", ZonedDateTime.now(), false, true, user);

        when(todoRepository.findByTodoId(4L)).thenReturn(todoTest);
        doNothing().when(todoRepository).deleteById(todoTest.getTodoId());
    }
}
