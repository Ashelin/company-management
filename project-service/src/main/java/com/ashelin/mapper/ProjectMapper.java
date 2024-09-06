package com.ashelin.mapper;

import com.ashelin.dto.ManagerRequest;
import com.ashelin.dto.ManagerResponse;
import com.ashelin.dto.ProjectRequest;
import com.ashelin.dto.ProjectResponse;
import com.ashelin.model.Manager;
import com.ashelin.model.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public Project toEntity(ProjectRequest projectRequest) {
        Manager manager = toEntity(projectRequest.getManager());
        return Project.builder()
                .projectName(projectRequest.getProjectName())
                .teamId(projectRequest.getTeamId())
                .manager(manager)
                .build();
    }

    public ProjectResponse toResponse(Project project) {
        return ProjectResponse.builder()
                .projectId(project.getId())
                .projectName(project.getProjectName())
                .teamId(project.getTeamId())
                .manager(toManagerResponse(project.getManager()))
                .creationTimestamp(project.getCreationTimestamp())
                .modificationTimestamp(project.getModificationTimestamp())
                .build();
    }

    public Manager toEntity(ManagerRequest managerRequest) {
        return Manager.builder()
                .managerName(managerRequest.getManagerName())
                .email(managerRequest.getEmail())
                .build();
    }

    private ManagerResponse toManagerResponse(Manager manager) {
        if (manager == null) {
            return null;
        }
        return ManagerResponse.builder()
                .managerId(manager.getId())
                .managerName(manager.getManagerName())
                .email(manager.getEmail())
                .build();
    }
}