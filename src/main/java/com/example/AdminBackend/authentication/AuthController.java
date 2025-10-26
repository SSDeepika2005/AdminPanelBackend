package com.example.AdminBackend.authentication;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AdminBackend.dto.LoginRequest;
import com.example.AdminBackend.model.User;
import com.example.AdminBackend.repository.UserRepo;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {

	 @Autowired
	    private UserRepo userRepo;

private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    @Autowired
	    private JwtUtil jwtUtil;   

	   
   @PostMapping("/login")
	    public Map<String, String> login(@RequestBody LoginRequest loginData) {
	        Map<String, String> res = new HashMap<>();
	        String email = loginData.getEmail();
	        String password = loginData.getPassword();
	        User user = userRepo.findByEmail(email);
	        String role = user.getRoles().iterator().next().getName(); 
	        System.out.println("User from DB: " + user);
	        System.out.println("Login attempt: " + email + " " + password);

	        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
	            String token = jwtUtil.generateToken(email, role);
	            res.put("token", token);
	            res.put("roles", role);
	            return res;
	        }

	        res.put("error", "Invalid credentials");
	        return res;
	    }
}
