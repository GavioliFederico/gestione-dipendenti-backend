package com.proj.finale.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.proj.finale.entity.Log;
import com.proj.finale.entity.LogResponse;
import com.proj.finale.entity.User;
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
    public List<Log> getLogsByUserId(int userId) {
    	return logrepository.findByUserId(userId);
    }

    @Override
    public Log updateLog(Log log) {
        // Verifica se il log esiste nel database prima di aggiornarlo
        if (logrepository.existsById(log.getId())) {
            return logrepository.save(log);
        } else {
           return null;
           // throw new LogNotFoundException("Log not found with ID: " + log.getId());
        }
    }
	@Override
	public Optional<Log> getLogById(int logId) {
		return logrepository.findById(logId);
	}

    public LogResponse getLastLogForUser(int userId) {
        Optional<Log> lastLog = logrepository.findTopByUserIdOrderByEntryTimeDesc(userId);

        return lastLog.map(LogResponse::new).orElse(null);
    }




}
