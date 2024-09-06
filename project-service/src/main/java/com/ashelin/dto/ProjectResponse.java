package com.ashelin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {

    private Long projectId;
    private String projectName;
    private Long teamId;
    private ManagerResponse manager;
    private LocalDateTime creationTimestamp;
    private LocalDateTime modificationTimestamp;
}