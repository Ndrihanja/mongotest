package com.ndrianja.mongotest.team;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/team")
@RequiredArgsConstructor
@Tag(name = "Team", description = "Endpoints for team management")
public class TeamController {

    private final TeamService teamService;
    @PostMapping("/create")
    public ResponseEntity<TeamAddResponse> createTeam(@RequestBody TeamAddRequest teamAddRequest) {
        return ResponseEntity.ok(teamService.createTeam(teamAddRequest));
    }
}
