package com.ashelin.mapper;

import com.ashelin.dto.CompanyRequest;
import com.ashelin.dto.CompanyResponse;
import com.ashelin.dto.DepartmentDto;
import com.ashelin.model.Company;
import org.springframework.stereotype.Component;

import java.util.List;;

@Component
public class CompanyMapper {

    public CompanyResponse toResponse(Company company, List<DepartmentDto> departments) {
        return CompanyResponse.builder()
                .id(company.getId())
                .companyName(company.getCompanyName())
                .departments(departments)
                .creationTimestamp(company.getCreationTimestamp())
                .modificationTimestamp(company.getModificationTimestamp())
                .build();
    }

    public Company toEntity(CompanyRequest companyRequest) {
        return Company.builder()
                .companyName(companyRequest.getCompanyName())
                .build();
    }
}