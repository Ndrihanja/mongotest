package com.ndrianja.mongotest.project;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
@Tag(name = "Project", description = "Endpoints for project management")
public class ProjectController {

    private final ProjectService projectService;
    @PostMapping("/create/team")
    public ResponseEntity<ProjectAddResponse> createProjectByTeam(@RequestBody ProjectAddByTeamRequest projectAddByTeamRequest) {
        return ResponseEntity.ok(projectService.createProjectByTeam(projectAddByTeamRequest));
    }
    @PostMapping("/create/member")
    public ResponseEntity<ProjectAddResponse> createProjectByUser(@RequestBody ProjectAddByMemberRequest projectAddByMemberRequest) {
        return ResponseEntity.ok(projectService.createProjectByMember(projectAddByMemberRequest));
    }
}
