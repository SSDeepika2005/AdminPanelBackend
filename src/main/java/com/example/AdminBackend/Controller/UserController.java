package com.example.AdminBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AdminBackend.model.User;
import com.example.AdminBackend.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class UserController {

	//this is admin
	@Autowired 
	UserService userService;
	@GetMapping("/api/adminactionGet")
	public List<User> actionget()    
	{        
		return userService. getinfofun();  
		   
	}     
	
	 @PostMapping("/api/users")
	    public User createUser(@RequestBody User user) {
	        return userService.createUser(user);
	    }
}
