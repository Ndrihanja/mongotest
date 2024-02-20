package com.ndrianja.mongotest.team;

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
public class TeamAddRequest {

    private String name;
    private List<User> team_members;
}
