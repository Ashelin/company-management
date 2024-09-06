package com.ashelin.service;

import com.ashelin.dto.ProjectRequest;
import com.ashelin.dto.ProjectResponse;
import com.ashelin.mapper.ProjectMapper;
import com.ashelin.model.Manager;
import com.ashelin.model.Project;
import com.ashelin.repository.ManagerRepository;
import com.ashelin.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ManagerRepository managerRepository;
    private final ProjectMapper projectMapper;

    public ResponseEntity<Void> createProject(ProjectRequest projectRequest) {
        Manager manager = projectMapper.toEntity(projectRequest.getManager());

        manager = managerRepository.save(manager);

        Project project = projectMapper.toEntity(projectRequest);
        project.setManager(manager);
        projectRepository.save(project);

        log.info("Project {} created successfully", project.getId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<List<ProjectResponse>> getProjectsByTeamId(Long teamId) {
        List<Project> projects = projectRepository.findByTeamId(teamId);
        List<ProjectResponse> response = projects.stream()
                .map(project -> {
                    ProjectResponse projectResponse = projectMapper.toResponse(project);
                    log.info("Project {} with manager {}", project.getId(), project.getManager());
                    return projectResponse;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<ProjectResponse> getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        ProjectResponse response = projectMapper.toResponse(project);
        return ResponseEntity.ok(response);
    }
}