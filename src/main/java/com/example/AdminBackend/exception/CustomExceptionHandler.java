package com.example.AdminBackend.exception;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class CustomExceptionHandler {

	
	  @ExceptionHandler(IllegalArgumentException.class)
	    public ResponseEntity<Map<String, String>> handleIllegal(IllegalArgumentException ex) {
	        return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
	    } 

	    @ExceptionHandler(ResponseStatusException.class)
	    public ResponseEntity<Map<String, String>> handleResponseStatus(ResponseStatusException ex) {
	        return ResponseEntity.status(ex.getStatusCode())
	                             .body(Map.of("message", ex.getReason()));
	    }

	    // Optional: other RuntimeExceptions
	    @ExceptionHandler(RuntimeException.class)
	    public ResponseEntity<Map<String, String>> handleRuntime(RuntimeException ex) {
	        return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
	    }
}

