package com.ndrianja.mongotest.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
