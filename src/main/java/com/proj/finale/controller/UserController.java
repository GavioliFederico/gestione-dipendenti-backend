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

import com.proj.finale.entity.PasswordUtils;
import com.proj.finale.entity.User;
import com.proj.finale.entity.UserLoginRequest;
import com.proj.finale.service.UserService;

import io.micrometer.common.util.StringUtils;

@CrossOrigin(origins = "*")
@RestController
public class UserController {
	
	@Autowired
	UserService userservice;
	
    @Autowired
    PasswordUtils passwordutils;

	public UserController(UserService userservice) {
		super();
		this.userservice = userservice;
	}
	
	////////////////////////CRUD-operations//////////////////////////
	
	@GetMapping("/users")
	public Iterable<User> usersList(){
		return userservice.getAllUser();
	}
	
	////////////////////////Get-single-user//////////////////////////
	
	
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        Optional<User> user = userservice.getUser(id);

        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    ////////////////////////Get-single-user-HASH//////////////////////////
    
    //NON SI PUO DEHASHARE
	
    /*
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        Optional<User> user = userservice.getUser(id);

        if (user.isPresent()) {
            // Dehasha la password prima di restituirla
            User userEntity = user.get();
            userEntity.setPassword(PasswordUtils.dehashPassword(userEntity.getPassword()));

            return new ResponseEntity<>(userEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    */
    
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
	
    ////////////////////////New   //////////////////////////
	
	/*
	@PostMapping("/users/new")
	public User newUser(@RequestBody(required=true)User newUser) {
		newUser=userservice.saveUser(newUser);
		return newUser;
	}
	*/
	
    ////////////////////////New-Hash//////////////////////////
	
    @PostMapping("/users/new")
    public User newUser(@RequestBody(required=true) User newUser) {
        // Hasha la password prima di salvarla nel database
        String hashedPassword = passwordutils.hashPassword(newUser.getPassword());
        newUser.setPassword(hashedPassword);

        newUser = userservice.saveUser(newUser);
        return newUser;
    }
	
    ////////////////////////Update-single-user//////////////////////////
	
    /*
    
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
    */
    
    ////////////////////////Update-single-user-HASH//////////////////////////
    
    @PutMapping("/users/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        Optional<User> existingUser = userservice.getUser(id);

        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();

            // Verifica se la password è stata fornita e se è diversa dalla password attuale
            if (!StringUtils.isBlank(updatedUser.getPassword())) {
            	
            	System.out.println("nuovapassword :"+updatedUser.getPassword());
                // Hasha la nuova password prima di aggiornarla nel database
                String hashedPassword = PasswordUtils.hashPassword(updatedUser.getPassword());
                userToUpdate.setPassword(hashedPassword);
            }

            // Mantieni i valori originali se i nuovi valori sono null
            userToUpdate.setEmail(updatedUser.getEmail() != null ? updatedUser.getEmail() : userToUpdate.getEmail());
            userToUpdate.setName(updatedUser.getName() != null ? updatedUser.getName() : userToUpdate.getName());
            userToUpdate.setSurname(updatedUser.getSurname() != null ? updatedUser.getSurname() : userToUpdate.getSurname());
            userToUpdate.setRole(updatedUser.getRole() != null ? updatedUser.getRole() : userToUpdate.getRole());

            User updatedUserEntity = userservice.editUser(userToUpdate);

            return new ResponseEntity<>(updatedUserEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	////////////////////////LogIn//////////////////////////
	
	/*
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
	*/
    
    ////////////////////////LogIn-Hash//////////////////////////
    
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserLoginRequest userLoginRequest) {
        String email = userLoginRequest.getEmail();
        String password = userLoginRequest.getPassword();

        Optional<User> userOptional = userservice.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Dehasha la password e verifica
            if (passwordutils.checkPassword(password, user.getPassword())) {
                System.out.println("Login successful");
                return ResponseEntity.ok(user);
            } else {
                System.out.println("Login failed");
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    


}
