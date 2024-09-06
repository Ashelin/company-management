package com.ashelin.controller;

import com.ashelin.dto.TeamRequest;
import com.ashelin.dto.TeamResponse;
import com.ashelin.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/teams")
public class TeamController {

    private final TeamService teamService;

    @PostMapping(value = "/create")
    public ResponseEntity<Void> createTeam(@RequestBody TeamRequest teamRequest) {
        return teamService.createTeam(teamRequest);
    }

    @GetMapping
    public ResponseEntity<List<TeamResponse>> getTeamsByDepartmentId(@RequestParam Long departmentId) {
        return teamService.getTeamsByDepartmentId(departmentId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamResponse> getTeamById(@PathVariable Long id) {
        return teamService.getTeamById(id);
    }
}