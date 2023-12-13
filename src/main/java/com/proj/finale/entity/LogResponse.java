package com.proj.finale.entity;

import java.sql.Timestamp;

public class LogResponse {
    private int id;
    private Timestamp entryTime;
    private Timestamp exitTime;
    private int userId;
    
	//////////////////////Constructors/////////////////////////////

    public LogResponse(Log log) {
        this.id = log.getId();
        this.entryTime = log.getEntry_time();
        this.exitTime = log.getExit_time();
        this.userId = log.getUser().getId();
    }
    
	//////////////////////Setter-Getter/////////////////////////////

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}

	public Timestamp getExitTime() {
		return exitTime;
	}

	public void setExitTime(Timestamp exitTime) {
		this.exitTime = exitTime;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}