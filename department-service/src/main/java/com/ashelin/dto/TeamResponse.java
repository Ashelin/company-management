package com.ashelin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamResponse {

    private Long teamId;
    private String teamName;
    private List<ProjectResponse> projects;
}