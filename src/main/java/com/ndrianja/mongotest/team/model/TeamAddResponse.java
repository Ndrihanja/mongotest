package com.ndrianja.mongotest.team.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class TeamAddResponse {
    @JsonProperty("name")
    private String name;
    @JsonProperty("team_members")
    private List<User> team_members;
}
