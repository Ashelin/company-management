package com.ashelin.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "PROJECT")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(name = "PROJECT_NAME", nullable = false)
    private String projectName;

    @Column(name = "TEAM_ID", nullable = false)
    private Long teamId;

    @OneToOne
    @JoinColumn(name = "MANAGER_ID", referencedColumnName = "id")
    private Manager manager;

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