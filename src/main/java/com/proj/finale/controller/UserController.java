package com.proj.finale.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@DeleteMapping("/deleteUser/{id}")
	public void deleteUser(@PathVariable Integer Id) {
		userservice.deleteUserById(Id);
	}
	
	@PostMapping("/users/new")
	public User newUser(@RequestBody(required=true)User newUser) {
		newUser=userservice.saveUser(newUser);
		return newUser;
	}
	
	//////////!!!!!!!!!!!!!!!!!//////////
	
	//modifica utente
	
	////////////////////////LogIn//////////////////////////
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody UserLoginRequest userLoginRequest) {
	    // Validate user credentials
	    String email = userLoginRequest.getEmail();
	    String password = userLoginRequest.getPassword();

	    Optional<User> userOptional = userservice.findByEmailAndPassword(email, password);

	    if (userOptional != null) {
	        User user = userOptional.get();
	        System.out.println("Login successful");
	      
	        return ResponseEntity.ok(user);
	    } else {
	       
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }
	}

}
