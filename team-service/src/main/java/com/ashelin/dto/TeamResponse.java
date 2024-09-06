package com.ashelin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamResponse {

    private Long teamId;
    private String teamName;
    private Long departmentId;
    private List<ProjectResponse> projects;
    private LocalDateTime creationTimestamp;
    private LocalDateTime modificationTimestamp;
}