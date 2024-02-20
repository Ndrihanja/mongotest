package com.ndrianja.mongotest.project.model;

import com.ndrianja.mongotest.project.Status;
import com.ndrianja.mongotest.team.model.Team;
import com.ndrianja.mongotest.user.model.User;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "projects")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
