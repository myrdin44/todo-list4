package org.example.todolist.controller;

import jakarta.validation.Valid;
import org.example.todolist.model.Todo;
import org.example.todolist.service.IMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo-list")
public class TodoController {
    @Autowired
    private IMService imService;

    //test db
    @GetMapping("/test-connection")
    public String test(){
        return "Database connected successfully!";
    }

    //API them task moi
    @PostMapping("/add")
    public Todo addTask(@Valid @RequestBody Todo newTask, @RequestParam("userId") long userId) {
        return imService.addTask(newTask, userId);
    }

    //API update task bang id
    @PutMapping("/update")
    public Todo updateTask(@RequestParam("id") long id,@Valid @RequestBody Todo todo) {
        return imService.updateTask(id, todo);
    }

    //API xoa task
    @DeleteMapping("/delete/{id}")
    public boolean deleteTask(@PathVariable("id") long id) {
        return imService.deleteTask(id);
    }

    //API mark as importtant
    @PostMapping("/mark/{id}")
    public boolean markAsImportant(@PathVariable("id") long id) {
        return imService.markAsImportant(id);
    }

    //API doi trang thai hoan thanh cua 1 task
    @PostMapping("/completed/{id}")
    public boolean isCompleted(@PathVariable("id") long id) {
        return imService.markAsComplete(id);
    }

    //API hien thi tat ca cac task
    @GetMapping("/display-all")
    public List<Todo> displayAllTask() {
        return imService.displayAllTask();
    }

    //API hien thi 1 task
    @GetMapping("/get-task/{id}")
    public Todo getATask(@PathVariable("id") long id) {
        return imService.getATask(id);
    }

    //get all task by UserId
    @GetMapping("/get-all-user-task/{userId}")
    public List<Todo> getAllTasksByUserId(@PathVariable("userId") long userId) {
        return imService.getAllTaksByUserId(userId);
    }
}