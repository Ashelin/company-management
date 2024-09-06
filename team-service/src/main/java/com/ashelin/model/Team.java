package com.ashelin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "TEAM")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(name = "TEAM_NAME", nullable = false)
    private String teamName;
    @Column(name = "DEPARTMENT_ID", nullable = false)
    private Long departmentId;
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
