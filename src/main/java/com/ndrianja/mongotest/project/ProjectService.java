package com.ndrianja.mongotest.project;

import com.ndrianja.mongotest.team.Team;
import com.ndrianja.mongotest.team.TeamService;
import com.ndrianja.mongotest.user.User;
import com.ndrianja.mongotest.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserService userService;

    private final TeamService teamService;

    public ProjectAddResponse createProjectByMember(ProjectAddByMemberRequest projectAddByMemberRequest) {
        User createdByUser = userService.findUserById(projectAddByMemberRequest.getCreatedByUsersId().toString())
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));

        Project created_project = Project.builder()
                .name(projectAddByMemberRequest.getName())
                .description(projectAddByMemberRequest.getDescription())
                .createdByUsersId( projectAddByMemberRequest.getCreatedByUsersId())
                .collaboratingMembers(projectAddByMemberRequest.getCollaboratingMembers().stream().toList())
                .build();
        return ProjectAddResponse.builder()
                .name(created_project.getName())
                .description(created_project.getDescription())
                .build();
    }

    public ProjectAddResponse createProjectByTeam(ProjectAddByTeamRequest projectAddByTeamRequest) {
        Team createdByTeam = teamService.findTeamById(projectAddByTeamRequest.getCreatedByTeamId().toString())
                .orElseThrow(() -> new IllegalArgumentException("Team non trouvé"));

        Project created_project = Project.builder()
                .name(projectAddByTeamRequest.getName())
                .description(projectAddByTeamRequest.getDescription())
                .createdByTeamId( projectAddByTeamRequest.getCreatedByTeamId())
                .collaboratingMembers(projectAddByTeamRequest.getCollaboratingMembers().stream().toList())
                .build();
        return ProjectAddResponse.builder()
                .name(created_project.getName())
                .description(created_project.getDescription())
                .build();
    }
}
