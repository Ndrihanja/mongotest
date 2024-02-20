package com.ndrianja.mongotest.project.service;

import com.ndrianja.mongotest.project.model.Project;
import com.ndrianja.mongotest.project.model.ProjectAddByMemberRequest;
import com.ndrianja.mongotest.project.model.ProjectAddByTeamRequest;
import com.ndrianja.mongotest.project.model.ProjectAddResponse;

import java.util.List;

public interface ProjectService {
    ProjectAddResponse createProjectByMember(ProjectAddByMemberRequest projectAddByMemberRequest, String createdUserId);
    ProjectAddResponse createProjectByTeam(ProjectAddByTeamRequest projectAddByTeamRequest);
    List<Project> findAllProject();
}
