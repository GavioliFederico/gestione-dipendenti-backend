package com.proj.finale.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.proj.finale.entity.Log;
import com.proj.finale.entity.LogRequest;
import com.proj.finale.service.LogService;
import com.proj.finale.service.UserService;
import com.proj.finale.entity.User;

@RestController
@CrossOrigin(origins = "*")
public class LogController {
	
	@Autowired
	private LogService logservice;
	
	@Autowired
	private UserService userservice;

	public LogController(LogService logservice, UserService userservice) {
		super();
		this.logservice = logservice;
		this.userservice = userservice;
	}
	
	////////////////////////CRUD-operations//////////////////////////
	
	@GetMapping("/logs")
	public Iterable<Log> getLogs(){
		return logservice.getAllLog();
	}
	
	@PostMapping("/logs/new/{idUser}")
	public ResponseEntity<Log> newLog(@RequestBody LogRequest logrequest,@PathVariable(name = "idUser") int idUser){
		Optional<User> user = userservice.getUser(idUser);
		
		Log newLog = new Log();
		newLog.setEntry_time(logrequest.getEntry_time());
		newLog.setExit_time(logrequest.getExit_time());
		
		newLog.setUser(user.orElseThrow());
		
		Log createdLog = logservice.newLog(newLog);
		return new ResponseEntity<>(createdLog, HttpStatus.CREATED);
	}

}
