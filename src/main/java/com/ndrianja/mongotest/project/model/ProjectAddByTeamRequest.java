package com.ndrianja.mongotest.project.model;

import com.ndrianja.mongotest.project.Status;
import com.ndrianja.mongotest.team.model.Team;
import com.ndrianja.mongotest.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectAddByTeamRequest {

    private String name;
    private String description;
    private Status status;
    private Team createdByTeamId;
    private List<Team> collaboratingTeams;
    private List<User> collaboratingMembers;
}
