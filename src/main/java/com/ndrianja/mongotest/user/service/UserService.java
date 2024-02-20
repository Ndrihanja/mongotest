package com.ndrianja.mongotest.user.service;

import com.ndrianja.mongotest.exception.InvalidTokenException;
import com.ndrianja.mongotest.exception.UserAlreadyExistException;
import com.ndrianja.mongotest.mfa.model.MfaTokenData;
import com.ndrianja.mongotest.user.model.User;
import com.ndrianja.mongotest.user.model.UserAddRequest;
import com.ndrianja.mongotest.user.model.UserAddResponse;
import dev.samstevens.totp.exceptions.QrGenerationException;
import jakarta.mail.MessagingException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserAddResponse addUser(UserAddRequest user);
    List<User> findAll();
    Optional<User> findUserById(String userId);

    MfaTokenData registerUser(User user) throws UserAlreadyExistException, QrGenerationException;
    //MfaTokenData mfaSetup(String email) throws UnkownIdentifierException, QrGenerationException;
    boolean verifyTotp(final String code,String username);

    void sendRegistrationConfirmationEmail(final User user) throws MessagingException;
    boolean verifyUser(final String token) throws InvalidTokenException;
}
