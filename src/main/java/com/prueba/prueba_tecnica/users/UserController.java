package com.prueba.prueba_tecnica.users;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    
    private UserRepository userRepository;
    
    // GET SINGLE USER
    public UserController(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }
    
    // ALL USERS
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();   
    }
    
    // CREATE USER
    @PostMapping("/users/create")
    public User createUser(@RequestBody Map<String, String> json) {
        String name = json.get("name");
        String email = json.get("email");
        String phone = json.get("phone");
        User newUser = new User(name,phone,email);
        userRepository.save(newUser);
        return newUser;
    }
    
    // GET USER BY ID
    @GetMapping("users/{id}")
    public User retrieveUserById(@PathVariable Integer id) {
        User findUser = userRepository.getUserById(id);
        return findUser;
    }
    
    // DELETE USER BY ID
    @DeleteMapping("users/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable Integer id) {
        userRepository.delete(userRepository.getUserById(id));
        return new ResponseEntity<Object>("User deleted succesfully",null, 200);
    }
    
    
}
