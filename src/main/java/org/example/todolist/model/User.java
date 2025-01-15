package org.example.todolist.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.example.todolist.Enum.DegreeName;
import org.example.todolist.Enum.FacultyOrDepartment;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_db")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long userId;

    @NotBlank(message = "Username must not be blank!")
    private String username;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email!")
    @NotBlank(message = "Email must not be blank!")
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$", message = "Password must have 8-20 characters, Include uppercase, lowercase letters, digits and special characters!")
    @NotBlank(message = "Password must not be blank!")
    private String password;

    private String role;

    private String fullName;

    @Pattern(regexp = "^(0|\\+84)[3-9][0-9]{8}$", message = "Invalid phone!")
    @NotBlank(message = "Phone must not be blank!")
    private String phone;

    @Enumerated(EnumType.STRING)
    private FacultyOrDepartment facultyOrDepartment;

    private Boolean status;

    @Enumerated(EnumType.STRING)
    private DegreeName degreeName;

    private String subject; //doi tuong

    @Column(name = "system_level", nullable = false)
    private int systemLevel;

    public User(Long userId, String username, String email, String password, String role, String fullName, String phone, FacultyOrDepartment facultyOrDepartment, boolean status, DegreeName degreeName, String subject, int systemLevel) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.fullName = fullName;
        this.phone = phone;
        this.facultyOrDepartment = facultyOrDepartment;
        this.status = status;
        this.degreeName = degreeName;
        this.subject = subject;
        this.systemLevel = systemLevel;
    }

    public User() {}

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Todo> tasks = new ArrayList<>();

    public DegreeName getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(DegreeName degreeName) {
        this.degreeName = degreeName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getSystemLevel() {
        return systemLevel;
    }

    public void setSystemLevel(int systemLevel) {
        this.systemLevel = systemLevel;
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

    public FacultyOrDepartment getFacultyOrDepartment() {
        return facultyOrDepartment;
    }

    public void setFacultyOrDepartment(FacultyOrDepartment facultyOrDepartment) {
        this.facultyOrDepartment = facultyOrDepartment;
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
