package com.proj.finale.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Log {
	
	//////////////////////Log-entity/////////////////////////////
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private Timestamp entry_time;
	
	private Timestamp exit_time;
	
	//////////////////////Foreign-key/////////////////////////////
	
	@ManyToOne
	@JoinColumn(name="user_id",nullable=false)
	private User user;
	
	//////////////////////Constructors/////////////////////////////
	
	public Log() {
		super();
	}

	public Log(int id, Timestamp entry_time, Timestamp exit_time, User user) {
		super();
		this.id = id;
		this.entry_time = entry_time;
		this.exit_time = exit_time;
		this.user = user;
	}
	
	//////////////////////Setter-Getter/////////////////////////////
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getEntry_time() {
		return entry_time;
	}

	public void setEntry_time(Timestamp entry_time) {
		this.entry_time = entry_time;
	}

	public Timestamp getExit_time() {
		return exit_time;
	}

	public void setExit_time(Timestamp exit_time) {
		this.exit_time = exit_time;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	//////////////////////To-String/////////////////////////////////
	
	@Override
	public String toString() {
		return "Log [entry_time=" + entry_time + ", exit_time=" + exit_time + ", user=" + user + "]";
	}

}
