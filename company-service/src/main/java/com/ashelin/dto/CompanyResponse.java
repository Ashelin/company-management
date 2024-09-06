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
public class CompanyResponse {

    private Long id;
    private String companyName;
    private List<DepartmentDto> departments;
    private LocalDateTime creationTimestamp;
    private LocalDateTime modificationTimestamp;
}