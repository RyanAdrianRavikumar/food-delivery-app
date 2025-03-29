package com.foodapp.user.controller;

import com.foodapp.user.entity.User;
import com.foodapp.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://192.168.1.18:3000")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping(path = "/users/{id}")
    public User getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @PostMapping(path = "/users/register")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        try {
            // Register the user
            String response = userService.registerUser(user);

            // Check the response and set the appropriate HTTP status
            if (response.equals("User registered successfully.")) {
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            // Catch any exception and return a 500 Internal Server Error with the message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing the request: " + e.getMessage());
        }
    }

    @PostMapping(path = "/users/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        return userService.loginUser(user);
    }

    @GetMapping(path = "/users/{userId}/email")
    public String getUserByEmail(@PathVariable int userId){
        return userService.getUserByEmail(userId);
    }
}
