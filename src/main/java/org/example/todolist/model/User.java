package org.example.todolist.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_db")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long userId;

    private String username;
    private String email;
    private String password;
    private String role;
    private String fullName;
    private String phone;
    private String falcultyOrDepartment;
    private Boolean status;
    private String degreeName;

    public User(Long userId, String username, String email, String password, String role, String fullName, String phone, String falcultyOrDepartment, boolean status, String degreeName) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.fullName = fullName;
        this.phone = phone;
        this.falcultyOrDepartment = falcultyOrDepartment;
        this.status = status;
        this.degreeName = degreeName;
    }

    public User() {}

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Todo> tasks = new ArrayList<>();

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFalcultyOrDepartment() {
        return falcultyOrDepartment;
    }

    public void setFalcultyOrDepartment(String falcultyOrDepartment) {
        this.falcultyOrDepartment = falcultyOrDepartment;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    public List<Todo> getTasks() {
        return tasks;
    }

    public void setTasks(List<Todo> tasks) {
        this.tasks = tasks;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long id) {
        this.userId = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
