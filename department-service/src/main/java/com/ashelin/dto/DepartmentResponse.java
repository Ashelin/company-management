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
public class DepartmentResponse {

    private Long departmentId;
    private String departmentName;
    private Long companyId;
    private List<TeamResponse> teams;
    private LocalDateTime creationTimestamp;
    private LocalDateTime modificationTimestamp;
}