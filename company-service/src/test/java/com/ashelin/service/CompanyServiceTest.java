package com.ashelin.service;

import com.ashelin.dto.CompanyRequest;
import com.ashelin.dto.CompanyResponse;
import com.ashelin.dto.DepartmentDto;
import com.ashelin.dto.SearchCompany;
import com.ashelin.exception.CompanyNotFoundException;
import com.ashelin.mapper.CompanyMapper;
import com.ashelin.model.Company;
import com.ashelin.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private CompanyMapper companyMapper;

    @InjectMocks
    private CompanyService companyService;

    @Test
    void createCompany_shouldCreateCompanySuccessfully() {
        CompanyRequest companyRequest = new CompanyRequest("test company name");
        Company company = new Company();
        company.setCompanyName(companyRequest.getCompanyName());

        when(companyMapper.toEntity(companyRequest)).thenReturn(company);

        ResponseEntity<Void> response = companyService.createCompany(companyRequest);

        verify(companyRepository, times(1)).save(company);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void updateCompany_shouldUpdateExistingCompany() {
        Long companyId = 1L;
        CompanyRequest companyRequest = new CompanyRequest("updated company name");
        Company existingCompany = new Company();
        existingCompany.setCompanyName("old company name");

        when(companyRepository.findById(companyId)).thenReturn(Optional.of(existingCompany));

        ResponseEntity<Void> response = companyService.updateCompany(companyId, companyRequest);

        verify(companyRepository, times(1)).save(existingCompany);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateCompany_shouldThrowExceptionWhenCompanyNotFound() {
        Long companyId = 1L;
        CompanyRequest companyRequest = new CompanyRequest("updated company name");

        when(companyRepository.findById(companyId)).thenReturn(Optional.empty());

        assertThrows(CompanyNotFoundException.class, () -> companyService.updateCompany(companyId, companyRequest));
    }

    @Test
    void deleteCompany_shouldDeleteExistingCompany() {
        Long companyId = 1L;

        when(companyRepository.existsById(companyId)).thenReturn(true);

        ResponseEntity<Void> response = companyService.deleteCompany(companyId);

        verify(companyRepository, times(1)).deleteById(companyId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteCompany_shouldReturnForbiddenIfCompanyDoesNotExist() {
        Long companyId = 1L;

        when(companyRepository.existsById(companyId)).thenReturn(false);

        ResponseEntity<Void> response = companyService.deleteCompany(companyId);

        verify(companyRepository, never()).deleteById(companyId);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    void searchCompanies_shouldReturnNotFoundIfNoCompaniesMatch() {
        SearchCompany searchCompany = new SearchCompany(1L, "non-existent company");

        when(companyRepository.findAll(any(Specification.class))).thenReturn(Collections.emptyList());

        ResponseEntity<Set<CompanyResponse>> response = companyService.searchCompanies(searchCompany);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}