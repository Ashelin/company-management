package com.ashelin.service;

import com.ashelin.dto.CompanyRequest;
import com.ashelin.dto.CompanyResponse;
import com.ashelin.dto.DepartmentDto;
import com.ashelin.dto.SearchCompany;
import com.ashelin.exception.CompanyNotFoundException;
import com.ashelin.mapper.CompanyMapper;
import com.ashelin.model.Company;
import com.ashelin.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final WebClient webClient;

    public ResponseEntity<Void> createCompany(CompanyRequest companyRequest) {
        Company company = companyMapper.toEntity(companyRequest);
        companyRepository.save(company);
        log.info("Company {} created successfully", company.getId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<Void> updateCompany(Long id, CompanyRequest companyRequest) {
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Company not found with id: " + id));
        Optional.ofNullable(companyRequest.getCompanyName()).ifPresent(existingCompany::setCompanyName);
        companyRepository.save(existingCompany);
        log.info("Company {} updated successfully", id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity<Void> deleteCompany(Long id) {
        if (!companyRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent company with id: {}", id);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        companyRepository.deleteById(id);
        log.info("Company {} deleted successfully", id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity<Set<CompanyResponse>> searchCompanies(SearchCompany searchCompany) {
        List<Company> companies = companyRepository.findAll(
                Specification.where(CompanySpecification.hasCompanyName(searchCompany.getCompanyName()))
                        .and(CompanySpecification.hasId(searchCompany.getId()))
        );

        if (companies.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Set<CompanyResponse> companyResponses = companies.stream()
                .map(company -> {
                    List<DepartmentDto> departments = webClient.get()
                            .uri("http://localhost:8081/api/departments?companyId=" + company.getId())
                            .retrieve()
                            .bodyToFlux(DepartmentDto.class)
                            .collectList()
                            .onErrorResume(throwable -> {
                                log.warn("Failed to fetch departments for company {}, returning empty list", company.getId());
                                return Mono.just(List.of());
                            })
                            .block();

                    return companyMapper.toResponse(company, departments);
                })
                .collect(Collectors.toSet());

        return ResponseEntity.ok().body(companyResponses);
    }
}