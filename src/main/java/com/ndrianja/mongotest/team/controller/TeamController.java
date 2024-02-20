package com.ndrianja.mongotest.team.controller;

import com.ndrianja.mongotest.team.model.Team;
import com.ndrianja.mongotest.team.model.TeamAddRequest;
import com.ndrianja.mongotest.team.model.TeamAddResponse;
import com.ndrianja.mongotest.team.service.TeamService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping()
    public ResponseEntity<List<Team>> getAllTeam() {
        return ResponseEntity.ok().body(teamService.findAll());
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<Team> getTeamById(@PathVariable String teamId) {
        return teamService.findTeamById(teamId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
