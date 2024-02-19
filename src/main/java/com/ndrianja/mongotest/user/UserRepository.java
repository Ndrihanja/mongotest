package com.ndrianja.mongotest.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {

    @Override
    Optional<User> findById(String s);
}
