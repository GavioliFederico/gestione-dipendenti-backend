package com.proj.finale.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {
	
	//////////////////////User-entity/////////////////////////////
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String email;
	
	private String password;
	
	private String name;
	
	private String surname;
	
	private String role;
	
	//////////////////////Foreign-key/////////////////////////////
	
	//One to many relation between user and log
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonBackReference
	private List<Log> log;
	
	//////////////////////Constructors/////////////////////////////
	
	public User() {
		super();
	}

	public User(int id, String email, String password, String name, String surname, String role, List<Log> log) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.role = role;
		this.log = log;
	}
	
	//////////////////////Setter-Getter/////////////////////////////
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Log> getLog() {
		return log;
	}

	public void setTicket(List<Log> log) {
		this.log = log;
	}
	
	//////////////////////To-String/////////////////////////////////
	
	@Override
	public String toString() {
		return "User [surname=" + surname + ", log=" + log + "]";
	}

}
