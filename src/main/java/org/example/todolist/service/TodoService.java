package org.example.todolist.service;

import org.example.todolist.model.Todo;
import org.example.todolist.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService implements IMService{
    @Autowired
    private TodoRepository todoRepository;


    @Override
    public Todo addTask(Todo newTask) {
        if(newTask != null) {
            return todoRepository.save(newTask);
        }
        return null;
    }

    @Override
    public Todo updateTask(long id, Todo todo) {
        if(todo != null) {
            Todo todo1 = todoRepository.getById(id);
            if(todo1 != null) {
                todo1.setCompleted(todo.isCompleted());
                todo1.setDescription(todo.getDescription());
                todo1.setCreatedAt(todo.getCreatedAt());
                todo1.setImportant(todo.isImportant());
                todo1.setTitle(todo.getTitle());
                return todo1;
            }
        }
        return null;
    }

    @Override
    public boolean deleteTask(long id) {
        if(id > 0) {
            Todo todo = todoRepository.getById(id);
            if(todo != null) {
                todoRepository.delete(todo);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean markAsImportant(long id) {
        if (id > 0) {
            Todo todo = todoRepository.getById(id);
            if (todo != null) {
                todo.setImportant(true);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean markAsComplete(long id) {
        if(id > 0) {
            Todo todo = todoRepository.getById(id);
            if(todo != null) {
                todo.setCompleted(true);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Todo> displayAllTask() {
        return todoRepository.findAll();
    }

    @Override
    public Todo getATask(long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found!"));
        return todo;
    }

    @Override
    public List<Todo> getAllTaksByUserId(long userId) {
        return todoRepository.findByUserId(userId);
    }
}
