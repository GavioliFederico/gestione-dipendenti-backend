package com.proj.finale.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.proj.finale.entity.Log;
import com.proj.finale.repository.LogRepository;
import com.proj.finale.service.LogService;

@Service
public class LogServiceImpl implements LogService{
	
	//////////////////////Repository////////////////////////
	
	LogRepository logrepository;
	
	//////////////////////Constructor////////////////////////
	
	public LogServiceImpl(LogRepository logrepository) {
		super();
		this.logrepository = logrepository;
	}
	
	//////////////////////Service-Impl////////////////////////

	@Override
	public List<Log> getAllLog() {
		return logrepository.findAll();
	}

	@Override
	public Log newLog(Log newLog) {
		return logrepository.save(newLog);
	}

	@Override
	public Optional<Log> getLogById(Integer logId) {
		return logrepository.findById(logId);
	}

}
