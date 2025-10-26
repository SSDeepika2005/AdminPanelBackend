package com.example.AdminBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AdminBackend.model.SystemLogs;
import com.example.AdminBackend.service.SystemLogsService;

@RestController 
public class SystemLogsController {

	@Autowired 
	SystemLogsService sysLogService;
	@GetMapping("/api/systemlogs") 
	public List<SystemLogs> getlogs()  
	{    
		  return sysLogService.fetchLog();
	}
}
