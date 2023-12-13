package com.proj.finale.entity;

import java.sql.Timestamp;

public class LogRequest {
	
	private Timestamp entryTime;
	
	private Timestamp exitTime;

	////////////////////Constructor///////////////////
	
	public LogRequest() {
		super();
	}
	
	public LogRequest(Timestamp entry_time, Timestamp exit_time) {
		super();
		this.entryTime = entry_time;
		this.exitTime = exit_time;
	}
	
	////////////////////Setters-Getters///////////////////
	
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
	
	////////////////////To-String///////////////////
	
	@Override
	public String toString() {
		return "LogRequest [entry_time=" + entryTime + ", exit_time=" + exitTime + "]";
	}

}
