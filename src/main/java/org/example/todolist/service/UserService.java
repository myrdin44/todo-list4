package org.example.todolist.service;

import org.example.todolist.Enum.DegreeName;
import org.example.todolist.Enum.FacultyOrDepartment;
import org.example.todolist.model.User;
import org.example.todolist.repository.UserRepository;
import org.example.todolist.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //find an user by their username
    public UserDetails findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return new CustomUserDetails(user);
    }

    //create an user
    public void save(User user) {
        userRepository.save(user);
    }

    //delete an user by id
    public boolean deleteUser(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        }

        return false;
    }

    //update an user's information
    public User updateUser(long userId, User newUser) {
        if (userRepository.existsById(userId)) {
            User user = userRepository.findById(userId).orElse(null);
            assert user != null;
            user.setUsername(newUser.getUsername());
            user.setPassword(newUser.getPassword());
            user.setEmail(newUser.getEmail());
            user.setFullName(newUser.getFullName());
            user.setPhone(newUser.getPhone());
            user.setRole(newUser.getRole());
            try {
                FacultyOrDepartment faculty = newUser.getFacultyOrDepartment();
                user.setFacultyOrDepartment(faculty);
            } catch (IllegalArgumentException e) {
                throw new InputMismatchException("Invalid faculty or department value.");
            }
            user.setSubject(newUser.getSubject());
            user.setSystemLevel(newUser.getSystemLevel());
            user.setStatus(newUser.getStatus());
            user.setDegreeName(newUser.getDegreeName());

            return userRepository.save(user);
        }
        return null;
    }

    //get faculty list
    public List<String> getFacultyList() {
        return Arrays.stream(FacultyOrDepartment.values()).map(Enum::toString).collect(Collectors.toList());
    }
    //get degree name list
    public List<String> getDegreeList() {
        return Arrays.stream(DegreeName.values()).map(Enum::toString).collect(Collectors.toList());
    }

    //get an user by user id
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found with id " + userId));
    }

    //filter users by their faculty
    public List<User> getUserByFacultyOrDepartment(String department) {
        return userRepository.findByFacultyOrDepartment(department);
    }

    //get all users in database
    public Page<User> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("fullName").ascending());
        return userRepository.findAllUser(pageable);
    }

    //
    public UserDetails getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }

    //get user by their name
    public User getUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    //search user by their name
    public Page<User> filterUserByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("fullName").ascending());
        if (name == null || name.isEmpty()) {
            return Page.empty(pageable);
        }
        return userRepository.filterByFullName(name, pageable);
    }



}
