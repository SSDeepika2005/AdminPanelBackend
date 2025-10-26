package com.example.AdminBackend.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor   
@Table(name="users")
public class User {

    @Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	private String name;
//	private String role;
	private String email;
	private String firstName;
	private String lastName;    
	private boolean isActive;
	private  LocalDateTime createDate;
	private LocalDateTime lastLogin;
	private String password;
	
	 @ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(name = "user_roles", 
	       joinColumns = @JoinColumn(name = "user_id"),
	       inverseJoinColumns = @JoinColumn(name = "role_id"))
	 
	 @Builder.Default  
	    private Set<Role> roles = new HashSet<>();  
	
}
