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
    public class TeamRequest {

        private String teamName;
        private Long departmentId;
        private List<Long> projectIds;
    }