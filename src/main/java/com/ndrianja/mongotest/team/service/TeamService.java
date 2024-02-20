package com.ndrianja.mongotest.team.service;

import com.ndrianja.mongotest.team.model.Team;
import com.ndrianja.mongotest.team.model.TeamAddRequest;
import com.ndrianja.mongotest.team.model.TeamAddResponse;

import java.util.List;
import java.util.Optional;

public interface TeamService {

    TeamAddResponse createTeam(TeamAddRequest teamAddRequest);
    List<Team> findAll();
    Optional<Team> findTeamById(String teamId);
}
