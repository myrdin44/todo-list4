package org.example.todolist.repository;

import org.example.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.userId = :userId")
    User findByUserId(@Param("userId") Long userId);

    @Query("SELECT u FROM User u WHERE u.falcultyOrDepartment = :falcultyOrDepartment")
    List<User> findByFalcultyOrDepartment(@Param("falcultyOrDepartment") String falcultyOrDepartment);
}
