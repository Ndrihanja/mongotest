package com.ndrianja.mongotest.team.repository;

import com.ndrianja.mongotest.team.model.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamRepository extends MongoRepository<Team,String> {
}
