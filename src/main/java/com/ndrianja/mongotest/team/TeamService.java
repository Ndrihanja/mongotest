package com.ndrianja.mongotest.team;

import com.ndrianja.mongotest.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

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
}
