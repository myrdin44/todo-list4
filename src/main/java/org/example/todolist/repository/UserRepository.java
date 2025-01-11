package org.example.todolist.repository;

import org.example.todolist.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u")
    Page<User> findAllUser(Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.userId = :userId")
    User findByUserId(@Param("userId") Long userId);

    @Query("SELECT u FROM User u WHERE u.facultyOrDepartment = :facultyOrDepartment")
    List<User> findByFacultyOrDepartment(@Param("facultyOrDepartment") String facultyOrDepartment);

    @Query("SELECT u FROM User u WHERE LOWER(u.fullName) LIKE lower(CONCAT('%', :name, '%'))")
    Page<User> filterByFullName(@Param("name") String name, Pageable pageable);
}
