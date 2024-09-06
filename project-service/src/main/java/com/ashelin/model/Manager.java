package com.ashelin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "MANAGER")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(name = "MANAGER_NAME", nullable = false)
    private String managerName;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "CREATION_TIMESTAMP", updatable = false)
    private LocalDateTime creationTimestamp;

    @Column(name = "MODIFICATION_TIMESTAMP")
    private LocalDateTime modificationTimestamp;

    @PrePersist
    protected void onCreate() {
        creationTimestamp = LocalDateTime.now();
        modificationTimestamp = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        modificationTimestamp = LocalDateTime.now();
    }
}