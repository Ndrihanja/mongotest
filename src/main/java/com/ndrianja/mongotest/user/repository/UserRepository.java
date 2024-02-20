package com.ndrianja.mongotest.user.repository;

import com.ndrianja.mongotest.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {

    @Override
    Optional<User> findById(String s);

    Optional<User> findByEmail(String email);
}
