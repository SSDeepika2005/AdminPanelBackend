package com.example.AdminBackend.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.AdminBackend.model.User;

public class CustomerUserDetails implements UserDetails {

    private final User user;
	public CustomerUserDetails(User user)
	{
		this.user=user;
	}
	    
	  
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return           
      user.getRoles().stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName()))
                   .collect(Collectors.toList()); 
    }
    //we are setting password from user to security user class.
    @Override public String getPassword(){ return user.getPassword(); }
    @Override public String getUsername(){ return user.getName(); }
    //we are saying that this account is valid and password is not expired
    @Override public boolean isAccountNonExpired(){return true;}
    @Override public boolean isAccountNonLocked(){return true;}
    @Override public boolean isCredentialsNonExpired(){return true;}
    @Override public boolean isEnabled(){return true;}
}
