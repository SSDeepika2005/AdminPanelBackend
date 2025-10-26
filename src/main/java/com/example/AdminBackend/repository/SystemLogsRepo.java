package com.example.AdminBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.AdminBackend.model.SystemLogs;

public interface SystemLogsRepo extends JpaRepository<SystemLogs, Long> {

}