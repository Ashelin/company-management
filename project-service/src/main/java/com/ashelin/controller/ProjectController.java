package com.ashelin.controller;

import com.ashelin.dto.ProjectRequest;
import com.ashelin.dto.ProjectResponse;
import com.ashelin.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping(value = "/create")
    public ResponseEntity<Void> createProject(@RequestBody ProjectRequest projectRequest) {
        return projectService.createProject(projectRequest);
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getProjectsByTeamId(@RequestParam Long teamId) {
        return projectService.getProjectsByTeamId(teamId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }
}
