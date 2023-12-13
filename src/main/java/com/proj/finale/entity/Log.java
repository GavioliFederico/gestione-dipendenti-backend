package com.proj.finale.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
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
	
	@Column(name = "entry_time")
	private Timestamp entryTime;
	
	@Column(name = "exit_time")
	private Timestamp exitTime;
	
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
		this.entryTime = entry_time;
		this.exitTime = exit_time;
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
		return entryTime;
	}

	public void setEntry_time(Timestamp entry_time) {
		this.entryTime = entry_time;
	}

	public Timestamp getExit_time() {
		return exitTime;
	}

	public void setExit_time(Timestamp exit_time) {
		this.exitTime = exit_time;
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
		return "Log [entry_time=" + entryTime + ", exit_time=" + exitTime + ", user=" + user + "]";
	}

}
