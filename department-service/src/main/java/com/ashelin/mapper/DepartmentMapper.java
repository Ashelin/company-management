package com.ashelin.mapper;

import com.ashelin.dto.DepartmentRequest;
import com.ashelin.dto.DepartmentResponse;
import com.ashelin.model.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public Department toEntity(DepartmentRequest departmentRequest) {
        return Department.builder()
                .departmentName(departmentRequest.getDepartmentName())
                .companyId(departmentRequest.getCompanyId())
                .build();
    }

    public DepartmentResponse toResponse(Department department) {
        return DepartmentResponse.builder()
                .departmentId(department.getId())
                .departmentName(department.getDepartmentName())
                .companyId(department.getCompanyId())
                .creationTimestamp(department.getCreationTimestamp())
                .modificationTimestamp(department.getModificationTimestamp())
                .build();
    }
}