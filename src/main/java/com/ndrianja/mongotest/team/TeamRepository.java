package com.ndrianja.mongotest.team;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamRepository extends MongoRepository<Team,String> {
}
