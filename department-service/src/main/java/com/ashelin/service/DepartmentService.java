package com.ashelin.service;

import com.ashelin.dto.DepartmentRequest;
import com.ashelin.dto.DepartmentResponse;
import com.ashelin.dto.TeamResponse;
import com.ashelin.mapper.DepartmentMapper;
import com.ashelin.model.Department;
import com.ashelin.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    private final WebClient webClient;

    public ResponseEntity<Void> createDepartment(DepartmentRequest departmentRequest) {
        Department department = departmentMapper.toEntity(departmentRequest);
        departmentRepository.save(department);
        log.info("Department {} created successfully", department.getId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<List<DepartmentResponse>> getDepartmentsByCompanyId(Long companyId) {
        List<Department> departments = departmentRepository.findByCompanyId(companyId);

        List<DepartmentResponse> response = departments.stream()
                .map(department -> {
                    List<TeamResponse> teams = webClient.get()
                            .uri("http://localhost:8082/api/teams?departmentId=" + department.getId())
                            .retrieve()
                            .bodyToFlux(TeamResponse.class)
                            .collectList()
                            .onErrorResume(throwable -> {
                                log.warn("Failed to fetch teams for department {}, returning empty list", department.getId());
                                return Mono.just(List.of());
                            })
                            .block();

                    DepartmentResponse departmentResponse = departmentMapper.toResponse(department);
                    departmentResponse.setTeams(teams);
                    return departmentResponse;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<DepartmentResponse> getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        DepartmentResponse response = departmentMapper.toResponse(department);
        return ResponseEntity.ok(response);
    }
}