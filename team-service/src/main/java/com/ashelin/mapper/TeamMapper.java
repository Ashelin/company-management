package com.ashelin.mapper;

import com.ashelin.dto.TeamRequest;
import com.ashelin.dto.TeamResponse;
import com.ashelin.model.Team;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {

    public Team toEntity(TeamRequest teamRequest) {
        return Team.builder()
                .teamName(teamRequest.getTeamName())
                .departmentId(teamRequest.getDepartmentId())
                .build();
    }

    public TeamResponse toResponse(Team team) {
        return TeamResponse.builder()
                .teamId(team.getId())
                .teamName(team.getTeamName())
                .departmentId(team.getDepartmentId())
                .creationTimestamp(team.getCreationTimestamp())
                .modificationTimestamp(team.getModificationTimestamp())
                .build();
    }
}