package org.example.todolist.service;

import org.example.todolist.model.Todo;
import org.example.todolist.model.User;
import org.example.todolist.repository.TodoRepository;
import org.example.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService implements IMService{
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Todo addTask(Todo newTask, Long userId) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new RuntimeException("User not found with id " + userId);
        }
        if(newTask != null) {
            newTask.setUser(user);
            return todoRepository.save(newTask);
        }
        return null;
    }

    @Override
    public Todo updateTask(long id, Todo todo) {
        if(userRepository.existsById(id)) {
            Todo todo1 = todoRepository.getById(id);

            todo1.setCompleted(todo.isCompleted());
            todo1.setDescription(todo.getDescription());
            todo1.setCreatedAt(todo.getCreatedAt());
            todo1.setImportant(todo.isImportant());
            todo1.setTitle(todo.getTitle());
            return todo1;
        }
        return null;
    }

    @Override
    public boolean deleteTask(long id) {
        if(id > 0) {
            Todo todo = todoRepository.getById(id);
            todoRepository.delete(todo);
            return true;
        }
        return false;
    }

    @Override
    public boolean markAsImportant(long id) {
        if (id > 0) {
            Todo todo = todoRepository.getById(id);

            todo.setImportant(true);
            return true;
        }
        return false;
    }

    @Override
    public boolean markAsComplete(long id) {
        if(id > 0) {
            Todo todo = todoRepository.getById(id);

            todo.setCompleted(true);
            return true;
        }
        return false;
    }

    @Override
    public List<Todo> displayAllTask() {
        return todoRepository.findAll();
    }

    @Override
    public Todo getATask(long id) {
        return todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found!"));
    }

    @Override
    public List<Todo> getAllTaksByUserId(long userId) {
        return todoRepository.findByUserId(userId);
    }
}
