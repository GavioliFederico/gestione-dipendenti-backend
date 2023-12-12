package com.proj.finale.service;

import java.util.List;
import java.util.Optional;

import com.proj.finale.entity.Log;

public interface LogService {
	
	List<Log> getAllLog();
	
	Log newLog(Log newLog);
	
	//Da log id? o da user id??
	
	Optional<Log> getLogById(Integer logId);
	
	//remove log? inutile?

}
