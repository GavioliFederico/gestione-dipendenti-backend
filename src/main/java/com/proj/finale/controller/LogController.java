package com.proj.finale.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proj.finale.entity.Log;
import com.proj.finale.entity.LogRequest;
import com.proj.finale.entity.LogResponse;
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
		//newLog.setExit_time(logrequest.getEntry_time());
		
		newLog.setUser(user.orElseThrow());
		
		Log createdLog = logservice.newLog(newLog);
		return new ResponseEntity<>(createdLog, HttpStatus.CREATED);
	}
	
	@PutMapping("/log/update/{logId}")
	public ResponseEntity<LogResponse> updateExitTime(@PathVariable int logId, @RequestBody Map<String, String> requestBody) {
	    Optional<Log> optionalLog = logservice.getLogById(logId);

	    if (optionalLog.isPresent()) {
	        Log logToUpdate = optionalLog.get();
	        String exitTime = requestBody.get("exitTime");
	        logToUpdate.setExit_time(Timestamp.valueOf(exitTime));

	        Log updatedLog = logservice.updateLog(logToUpdate);

	        //Creazione di un oggetto di risposta specifico per il log modificato
	        LogResponse logResponse = new LogResponse(updatedLog);

	        return new ResponseEntity<>(logResponse, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@GetMapping("/logs/user/{userId}")
	public ResponseEntity<List<LogResponse>> getLogsByUserId(@PathVariable int userId) {
	    List<Log> userLogs = logservice.getLogsByUserId(userId);

	    if (!userLogs.isEmpty()) {
	        //Mappa la lista di Log a una lista di LogResponse
	        List<LogResponse> logResponses = userLogs.stream()
	                .map(LogResponse::new)
	                .collect(Collectors.toList());

	        return new ResponseEntity<>(logResponses, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
    ////////////////////////Last-log-User//////////////////////////    
    
    @GetMapping("/lastlog/user/{userId}")
    public ResponseEntity<LogResponse> getLastLogForUser(@PathVariable int userId) {
        LogResponse lastLog = logservice.getLastLogForUser(userId);

        if (lastLog != null) {
            return ResponseEntity.ok(lastLog);
        } else {
            // Puoi restituire un 404 Not Found se non esiste alcun log per l'utente
            return ResponseEntity.notFound().build();
        }
    }

	
}
