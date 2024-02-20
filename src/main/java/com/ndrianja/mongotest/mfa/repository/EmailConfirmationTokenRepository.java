package com.ndrianja.mongotest.mfa.repository;

import com.ndrianja.mongotest.mfa.model.EmailConfirmationToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailConfirmationTokenRepository extends MongoRepository<EmailConfirmationToken,String> {
    EmailConfirmationToken findByToken(String token);
}
