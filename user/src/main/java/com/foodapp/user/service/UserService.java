package com.foodapp.user.service;

import com.foodapp.user.entity.User;
import com.foodapp.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUserById(int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        return null;
    }

    @Transactional
    public String registerUser(User user){
        // Check if email already exists
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return "Email is already in use."; // Email already exists
        }

        // Save user and send response message
        userRepository.save(user);
        return "User registered successfully.";
    }

    public ResponseEntity<?> loginUser(User user) {
        // Find the user by email
        User existingUser = userRepository.findByEmail(user.getEmail());

        // Check if the user exists
        if (existingUser == null) {
            // If user not found, return 404 Not Found
            return ResponseEntity.status(404).body("User not found");
        }

        // Validate the password
        if (user.getPassword().equals(existingUser.getPassword())) {
            // If password is correct, create a response with userId
            Map<String, Object> response = new HashMap<>();
            response.put("userId", existingUser.getUser_id());  // Store userId in response
            return ResponseEntity.ok(response);  // Return userId as JSON
        } else {
            // If password is incorrect, return 401 Unauthorized
            return ResponseEntity.status(401).body("Invalid password");
        }
    }

    public String getUserByEmail(int userId){
        return userRepository.findEmailById(userId);
    }
}
