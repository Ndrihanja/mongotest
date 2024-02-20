package com.ndrianja.mongotest.team.service.impl;

import com.ndrianja.mongotest.team.model.Team;
import com.ndrianja.mongotest.team.model.TeamAddRequest;
import com.ndrianja.mongotest.team.model.TeamAddResponse;
import com.ndrianja.mongotest.team.repository.TeamRepository;
import com.ndrianja.mongotest.team.service.TeamService;
import com.ndrianja.mongotest.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    @Override
    public TeamAddResponse createTeam(TeamAddRequest teamAddRequest) {
        List<User> team_members = teamAddRequest.getTeam_members().stream().toList();
        Team team_add = Team.builder()
                .name(teamAddRequest.getName())
                .team_members(team_members)
                .build();
        teamRepository.save(team_add);
        return TeamAddResponse.builder()
                .name(team_add.getName())
                .team_members(team_add.getTeam_members())
                .build();
    }

    @Override
    public List<Team> findAll() {
        List<Team> list = teamRepository.findAll();
        List<Team> listDTO = new ArrayList<>();

        for (Team u : list) {
            listDTO.add(new Team(u.getId(), u.getName(), u.getTeam_members()));
        }

        return listDTO;
    }

    @Override
    public Optional<Team> findTeamById(String teamId) {
        return teamRepository.findById(teamId);
    }
}
