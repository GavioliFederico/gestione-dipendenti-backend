package com.proj.finale.service;

import java.util.List;
import java.util.Optional;

import com.proj.finale.entity.User;

public interface UserService {
	
	List<User> getAllUser();
	
	void deleteUserById(Integer Id);
	
	User saveUser(User newUser);
	
	User editUser(User editedUser);
	
	Optional<User> getUser(Integer Id);
	
	Optional<User> findByEmailAndPassword(String email,String password);
	
	List<User> getUsersByRole(String role);

	Optional<User> findByEmail(String email);

}
