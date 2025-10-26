package com.example.AdminBackend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AdminBackend.model.SystemLogs;
import com.example.AdminBackend.repository.SystemLogsRepo;

@Service 
public class SystemLogsService {

	
	@Autowired
	SystemLogsRepo sysLogRepo;
	
	public void savelogs(String action, String detail, Long entityId,String entityType)
	{ 
		SystemLogs syslog=SystemLogs.builder().action(action).details(detail).entityId(entityId).entityType(entityType).timestamp(LocalDateTime.now()).build();
		
		sysLogRepo.save(syslog);
	}
	
	public List<SystemLogs>fetchLog()
	{
		return sysLogRepo.findAll();  
	}
	
}
