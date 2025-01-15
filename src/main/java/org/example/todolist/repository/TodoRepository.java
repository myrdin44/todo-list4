package org.example.todolist.repository;

import org.example.todolist.model.Todo;
import org.example.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query("SELECT td FROM Todo td WHERE td.todoId = :todoId")
    Todo findByTodoId(@Param("todoId") long id);

    @Query("SELECT t FROM Todo t WHERE t.user.userId = :userId")
    List<Todo> findByUserId(@Param("userId") long userId);
}
