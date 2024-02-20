package com.ndrianja.mongotest.project.service.impl;

import com.ndrianja.mongotest.project.Status;
import com.ndrianja.mongotest.project.model.Project;
import com.ndrianja.mongotest.project.model.ProjectAddByMemberRequest;
import com.ndrianja.mongotest.project.model.ProjectAddByTeamRequest;
import com.ndrianja.mongotest.project.model.ProjectAddResponse;
import com.ndrianja.mongotest.project.repository.ProjectRepository;
import com.ndrianja.mongotest.project.service.ProjectService;
import com.ndrianja.mongotest.team.model.Team;
import com.ndrianja.mongotest.team.service.TeamService;
import com.ndrianja.mongotest.user.model.User;
import com.ndrianja.mongotest.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final UserService userService;
    private final TeamService teamService;

    @Override
    public ProjectAddResponse createProjectByMember(ProjectAddByMemberRequest projectAddByMemberRequest, String createdUserId) {
        User createdByUser = userService.findUserById(createdUserId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));

        Status status = Status.TODO;

        if (createdByUser != null) {
            Project created_project = Project.builder()
                    .name(projectAddByMemberRequest.getName())
                    .description(projectAddByMemberRequest.getDescription())
                    .status(status)
                    .createdByUsersId(projectAddByMemberRequest.getCreatedByUsersId())
                    .collaboratingMembers(projectAddByMemberRequest.getCollaboratingMembers().stream().toList())
                    .build();
            projectRepository.save(created_project);
            return ProjectAddResponse.builder()
                    .name(created_project.getName())
                    .description(created_project.getDescription())
                    .status(created_project.getStatus())
                    .build();
        } else {
            // Gestion du cas où l'utilisateur créateur n'est pas trouvé
            throw new IllegalArgumentException("Utilisateur créateur non trouvé");
        }
    }

    @Override
    public ProjectAddResponse createProjectByTeam(ProjectAddByTeamRequest projectAddByTeamRequest) {
        Team createdByTeam = teamService.findTeamById(projectAddByTeamRequest.getCreatedByTeamId().toString())
                .orElseThrow(() -> new IllegalArgumentException("Team non trouvé"));

        Status status = Status.TODO;

        Project created_project = Project.builder()
                .name(projectAddByTeamRequest.getName())
                .description(projectAddByTeamRequest.getDescription())
                .status(status)
                .createdByTeamId(projectAddByTeamRequest.getCreatedByTeamId())
                .collaboratingMembers(projectAddByTeamRequest.getCollaboratingMembers().stream().toList())
                .build();
        projectRepository.save(created_project);
        return ProjectAddResponse.builder()
                .name(created_project.getName())
                .description(created_project.getDescription())
                .status(created_project.getStatus())
                .build();
    }

    @Override
    public List<Project> findAllProject() {
        List<Project> list = projectRepository.findAll();
        List<Project> listDTO = new ArrayList<>();

        for (Project p : list) {
            listDTO.add(new Project(p.getId(),
                    p.getName(),
                    p.getDescription(),
                    p.getStatus(),
                    p.getCreatedByUsersId(),
                    p.getCreatedByTeamId(),
                    p.getCollaboratingTeams(),
                    p.getCollaboratingMembers()));
        }

        return listDTO;
    }

}
