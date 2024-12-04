package org.example.todolist.service;

import org.example.todolist.model.Todo;

import java.util.List;

public interface IMService {
    //them task moi
    public Todo addTask(Todo newTask);

    //sua task
    public Todo updateTask(long id, Todo todo);

    //xoa task
    public boolean deleteTask(long id);

    //danh dau quan trong
    public boolean markAsImportant(long id);

    //check trang thai hoan thanh
    public boolean markAsComplete(long id);

    //hien thi tat ca cac task
    public List<Todo> displayAllTask();

    //hien thi 1 task bang id
    public Todo getATask(long id);
}
