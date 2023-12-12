package com.proj.finale.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.proj.finale.entity.User;
import com.proj.finale.entity.UserLoginRequest;
import com.proj.finale.service.UserService;

@CrossOrigin(origins = "*")
@RestController
public class UserController {
	
	@Autowired
	UserService userservice;

	public UserController(UserService userservice) {
		super();
		this.userservice = userservice;
	}
	
	////////////////////////CRUD-operations//////////////////////////
	
	@GetMapping("/users")
	public Iterable<User> usersList(){
		return userservice.getAllUser();
	}
	

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        Optional<User> user = userservice.getUser(id);

        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/users/employees")
    public ResponseEntity<List<User>> getEmployeeUsers() {
        List<User> employeeUsers = userservice.getUsersByRole("employee");

        if (!employeeUsers.isEmpty()) {
            return new ResponseEntity<>(employeeUsers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@DeleteMapping("/deleteUser/{Id}")
	public void deleteUser(@PathVariable Integer Id) {
		userservice.deleteUserById(Id);
	}
	
	@PostMapping("/users/new")
	public User newUser(@RequestBody(required=true)User newUser) {
		newUser=userservice.saveUser(newUser);
		return newUser;
	}
	
    @PutMapping("/users/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        Optional<User> existingUser = userservice.getUser(id);

        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();

            userToUpdate.setEmail(updatedUser.getEmail());
            userToUpdate.setPassword(updatedUser.getPassword());
            userToUpdate.setName(updatedUser.getName());
            userToUpdate.setSurname(updatedUser.getSurname());
            userToUpdate.setRole(updatedUser.getRole());

            User updatedUserEntity = userservice.editUser(userToUpdate);

            return new ResponseEntity<>(updatedUserEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	////////////////////////LogIn//////////////////////////
	
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody UserLoginRequest userLoginRequest) {
	    // Validate user credentials
	    String email = userLoginRequest.getEmail();
	    String password = userLoginRequest.getPassword();

	    Optional<User> userOptional = userservice.findByEmailAndPassword(email, password);
	    System.out.println(email);
	    System.out.println(password);
	    if (userOptional.isPresent()) {
	        User user = userOptional.get();
	        System.out.println("Login successful");
	      
	        return ResponseEntity.ok(user);
	    } else {
	    	System.out.println("ansheloo");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }
	}
	

}
