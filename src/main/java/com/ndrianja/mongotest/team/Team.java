package com.ndrianja.mongotest.team;

import com.ndrianja.mongotest.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Team {
    @Id
    private String id;
    private String name;
    private List<User> team_members;

}
