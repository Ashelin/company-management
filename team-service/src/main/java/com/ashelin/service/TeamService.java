package com.ashelin.service;

import com.ashelin.dto.ProjectResponse;
import com.ashelin.dto.TeamRequest;
import com.ashelin.dto.TeamResponse;
import com.ashelin.mapper.TeamMapper;
import com.ashelin.model.Team;
import com.ashelin.repository.TeamRepository;
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
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final WebClient webClient;


    public ResponseEntity<Void> createTeam(TeamRequest teamRequest) {
        Team team = teamMapper.toEntity(teamRequest);
        teamRepository.save(team);
        log.info("Team {} created successfully", team.getId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    public ResponseEntity<List<TeamResponse>> getTeamsByDepartmentId(Long departmentId) {
        List<Team> teams = teamRepository.findByDepartmentId(departmentId);
        List<TeamResponse> response = teams.stream()
                .map(team -> {
                    TeamResponse teamResponse = teamMapper.toResponse(team);
                    List<ProjectResponse> projects = webClient.get()
                            .uri("http://localhost:8083/api/projects?teamId=" + team.getId())
                            .retrieve()
                            .bodyToFlux(ProjectResponse.class)
                            .collectList()
                            .onErrorResume(throwable -> {
                                log.warn("Failed to fetch projects for team {}, returning empty list", team.getId());
                                return Mono.just(List.of());
                            })
                            .block();

                    teamResponse.setProjects(projects);
                    return teamResponse;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<TeamResponse> getTeamById(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found"));
        TeamResponse response = teamMapper.toResponse(team);
        List<ProjectResponse> projects = webClient.get()
                .uri("http://localhost:8083/api/projects?teamId=" + id)
                .retrieve()
                .bodyToFlux(ProjectResponse.class)
                .collectList()
                .onErrorResume(throwable -> {
                    log.warn("Failed to fetch projects for team {}, returning empty list", id);
                    return Mono.just(List.of());
                })
                .block();

        response.setProjects(projects);
        return ResponseEntity.ok(response);
    }

}