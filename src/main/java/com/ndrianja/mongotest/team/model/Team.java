package com.ndrianja.mongotest.team.model;

import com.ndrianja.mongotest.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;
@Document(collection = "teams")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Team {
    @Id
    private String id;
    private String name;
    @DocumentReference(collection = "users")
    private List<User> team_members;

}
