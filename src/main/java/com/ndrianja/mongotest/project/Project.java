package com.ndrianja.mongotest.project;

import com.ndrianja.mongotest.team.Team;
import com.ndrianja.mongotest.user.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;
import java.util.Optional;

@Document(collection = "projects")
@Data
@Builder
public class Project {
    @Id
    private String id;
    private String name;
    private String description;
    private Status status;
    @DocumentReference(collection = "users")
    private User createdByUsersId;
    @DocumentReference(collection = "teams")
    private Team createdByTeamId;
    @DocumentReference(collection = "teams")
    private List<Team> collaboratingTeams;
    @DocumentReference(collection = "users")
    private List<User> collaboratingMembers;
}
