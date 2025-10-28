package com.example.AdminBackend.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Bean
//     public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//         return config.getAuthenticationManager();
//     }

//    @Bean
// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//     System.out.println("✅ SecurityConfig loaded successfully with CORS support!");

//     http
//         .cors(cors -> {}) // ✅ Enable CORS (uses your WebMvcConfigurer bean from main class)
//         .csrf(csrf -> csrf.disable())
//         .authorizeHttpRequests(auth -> auth
//             .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll() // ✅ allow preflight
//             .requestMatchers("/auth/**").permitAll() // ✅ your existing public routes
//             .anyRequest().authenticated()
//         )
//         .formLogin(form -> form.disable())
//         .httpBasic(httpBasic -> httpBasic.disable());

//     return http.build();
// }
// }


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
public class SecurityConfig {

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