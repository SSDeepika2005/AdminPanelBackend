package com.example.AdminBackend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
@Table(name="SystemLogs")
@AllArgsConstructor 
@NoArgsConstructor  
@Builder
public class SystemLogs {
 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long logId;

	    @ManyToOne(fetch = FetchType.LAZY)   // Many logs can belong to one user
	    @JoinColumn(name = "userId", nullable = false)
	    private User user;

	    private String action;
	    private String entityType;
	    private Long entityId;
	    private LocalDateTime timestamp;
	    private String details;

}
