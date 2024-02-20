package com.ndrianja.mongotest.task.repository;

import com.ndrianja.mongotest.task.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task,String> {
}
