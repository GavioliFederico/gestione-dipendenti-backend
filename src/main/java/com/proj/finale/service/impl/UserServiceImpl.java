package com.proj.finale.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.proj.finale.entity.User;
import com.proj.finale.repository.UserRepository;
import com.proj.finale.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	//////////////////////Repository////////////////////////
	
	private UserRepository userrepository;
	
	//////////////////////Constructor////////////////////////
	
	public UserServiceImpl(UserRepository userrepository) {
		super();
		this.userrepository = userrepository;
	}
	
	//////////////////////Service-Impl////////////////////////	

	@Override
	public List<User> getAllUser() {
		return userrepository.findAll();
	}

	@Override
	public void deleteUserById(Integer Id) {
		userrepository.deleteById(Id);
	}

	@Override
	public User saveUser(User newUser) {
		return userrepository.save(newUser);
	}

	///////////!!!!!!!!!!!!!!!!!!!!!!!!//////////
	
	@Override
	public User editUser(User editedUser) {
		return userrepository.save(editedUser);
	}

	@Override
	public Optional<User> getUser(Integer Id) {
		return userrepository.findById(Id);
	}
	
	//////////////////////LogIn////////////////////////	

	@Override
	public Optional<User> findByEmailAndPassword(String email, String password) {
		Optional<User> user = userrepository.findByEmailAndPassword(email, password);
		return user;
	}

	@Override
	public List<User> getUsersByRole(String role) {
	    return userrepository.findByRole(role);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		Optional<User> user = userrepository.findByEmail(email);
		return user;
	}

}
