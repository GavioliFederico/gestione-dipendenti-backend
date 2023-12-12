package com.proj.finale.entity;

import java.sql.Timestamp;

public class LogRequest {
	
	private Timestamp entry_time;
	
	private Timestamp exit_time;

	////////////////////Constructor///////////////////
	
	public LogRequest() {
		super();
	}
	
	public LogRequest(Timestamp entry_time, Timestamp exit_time) {
		super();
		this.entry_time = entry_time;
		this.exit_time = exit_time;
	}
	
	////////////////////Setters-Getters///////////////////
	
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
	
	////////////////////To-String///////////////////
	
	@Override
	public String toString() {
		return "LogRequest [entry_time=" + entry_time + ", exit_time=" + exit_time + "]";
	}

}
