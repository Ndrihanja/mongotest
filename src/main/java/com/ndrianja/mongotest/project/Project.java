package com.ndrianja.mongotest.project;

import com.ndrianja.mongotest.user.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document(collection = "projects")
@Data
@Builder
public class Project {
    @Id
    private String id;
    private String description;
    private Status status;

    private List<User> members;
}
