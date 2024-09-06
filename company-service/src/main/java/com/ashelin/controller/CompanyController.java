package com.ashelin.controller;

import com.ashelin.dto.CompanyRequest;
import com.ashelin.dto.CompanyResponse;
import com.ashelin.dto.SearchCompany;
import com.ashelin.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(value = "/api/company")
@RequiredArgsConstructor
@Validated
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping(value = "/search")
    public ResponseEntity<Set<CompanyResponse>> searchCompanies(@ModelAttribute SearchCompany searchCompany) {
        return companyService.searchCompanies(searchCompany);
    }


    @PostMapping(value = "/create")
    public ResponseEntity<Void> createCompany(@Valid @RequestBody CompanyRequest companyRequest) {
        return companyService.createCompany(companyRequest);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Void> updateCompany(@PathVariable Long id, @Valid @RequestBody CompanyRequest companyRequest) {
        return companyService.updateCompany(id, companyRequest);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        return companyService.deleteCompany(id);
    }
}