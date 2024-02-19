package com.ndrianja.mongotest.project;

import com.ndrianja.mongotest.team.Team;
import com.ndrianja.mongotest.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;
import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectAddByMemberRequest {

    private String name;
    private String description;
    private User createdByUsersId;
    private List<User> collaboratingMembers;
}
