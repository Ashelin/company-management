package com.ashelin.controller;

import com.ashelin.dto.DepartmentRequest;
import com.ashelin.dto.DepartmentResponse;
import com.ashelin.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<Void> createDepartment(@RequestBody DepartmentRequest departmentRequest) {
        return departmentService.createDepartment(departmentRequest);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> getDepartmentsByCompanyId(@RequestParam Long companyId) {
        return departmentService.getDepartmentsByCompanyId(companyId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }
}