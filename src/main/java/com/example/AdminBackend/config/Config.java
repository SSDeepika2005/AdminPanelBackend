package com.example.AdminBackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity 
@EnableMethodSecurity(prePostEnabled=true)
public class Config {  

	 @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http .csrf(csrf -> csrf.disable())  // disable CSRF for testing
         .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()); // allow all requests
     return http.build();
	    }   
    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
}
