package com.ndrianja.mongotest.project.repository;

import com.ndrianja.mongotest.project.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<Project,String> {
}
