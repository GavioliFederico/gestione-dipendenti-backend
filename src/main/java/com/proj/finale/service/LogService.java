package com.proj.finale.service;

import java.util.List;
import java.util.Optional;

import com.proj.finale.entity.Log;
import com.proj.finale.entity.User;

public interface LogService {
	
	List<Log> getAllLog();
	
	Log newLog(Log newLog);
	
	//Da log id? o da user id??

	List<Log> getLogsByUserId(int userId);

	Log updateLog(Log logToUpdate);

	Optional<Log> getLogById(int logId);
	
	//remove log? inutile?

}
