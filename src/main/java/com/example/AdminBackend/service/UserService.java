package com.example.AdminBackend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.AdminBackend.model.Role;
import com.example.AdminBackend.model.User;
import com.example.AdminBackend.repository.RoleRepo;
import com.example.AdminBackend.repository.UserRepo;

@Service
public class UserService {
@Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getinfofun() {
        return userRepo.findAll();
    }

    public User createUser(User user) {
        user.setCreateDate(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword())); // encode password

        // Fetch roles from DB (avoid transient errors)
        Set<Role> attachedRoles = user.getRoles().stream()
            .map(role -> roleRepo.findByName(role.getName())
                .orElseThrow(() -> new RuntimeException("Role not found: " + role.getName())))
            .collect(Collectors.toSet());

        user.setRoles(attachedRoles);

        return userRepo.save(user);
    }
}
