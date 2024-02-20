package com.ndrianja.mongotest.task.model;

import com.ndrianja.mongotest.project.model.Project;
import com.ndrianja.mongotest.user.model.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document(collection = "tasks")
@Data
@Builder
public class Task {
    @Id
    private String id;
    private String description;
    private String status;

    private List<Project> project;

    private List<User> executors;

}
