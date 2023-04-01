package com.example.myproject.controller;

import com.example.myproject.util.CustomResponse;
import com.example.myproject.exception.UserNotFoundException;
import com.example.myproject.entity.User;
import com.example.myproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping("/{userId}")
//    public ResponseEntity<CustomResponse<User>> getUser(@PathVariable Long userId) {
//        User user = userService.getUserById(userId);
//        CustomResponse<User> response = new CustomResponse<>("User retrieved successfully", user, new Date());
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
@GetMapping("/{userId}")
public CustomResponse<User> getUser(@PathVariable Long userId) {
    User user = null;
    try {
        user = userService.getUserById(userId);
    } catch (UserNotFoundException ex) {
        throw ex;
    } catch (IllegalArgumentException ex) {
        throw ex;
    } catch (Exception ex) {
        throw new RuntimeException("Internal server error", ex);
    }
    CustomResponse<User> response = new CustomResponse<>("User retrieved successfully", user, new Date());
    return response;
}


    @PostMapping
    public CustomResponse<User> addUser(@RequestBody User user) {
        User addedUser = userService.addUser(user);
        CustomResponse<User> response = new CustomResponse<>("User added successfully", addedUser, new Date());
        return response;
    }

    @PutMapping("/{userId}")
    public CustomResponse<User> updateUser(@PathVariable Long userId, @RequestBody User user) {
        User updatedUser = userService.updateUser(userId, user);
        CustomResponse<User> response = new CustomResponse<>("User updated successfully", updatedUser, new Date());
        return response;
    }

    @DeleteMapping("/{userId}")
    public CustomResponse<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        CustomResponse<Void> response = new CustomResponse<>("User deleted successfully", null, new Date());
        return response;
    }
}

