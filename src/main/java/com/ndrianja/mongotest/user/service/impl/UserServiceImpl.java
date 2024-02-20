package com.ndrianja.mongotest.user.service.impl;

import com.ndrianja.mongotest.exception.InvalidTokenException;
import com.ndrianja.mongotest.exception.MFAServerAppException;
import com.ndrianja.mongotest.exception.UserAlreadyExistException;
import com.ndrianja.mongotest.mfa.model.EmailConfirmationToken;
import com.ndrianja.mongotest.mfa.model.MfaTokenData;
import com.ndrianja.mongotest.mfa.repository.EmailConfirmationTokenRepository;
import com.ndrianja.mongotest.mfa.service.EmailService;
import com.ndrianja.mongotest.mfa.service.TotpManager;
import com.ndrianja.mongotest.user.repository.UserRepository;
import com.ndrianja.mongotest.user.model.User;
import com.ndrianja.mongotest.user.model.UserAddRequest;
import com.ndrianja.mongotest.user.model.UserAddResponse;
import com.ndrianja.mongotest.user.service.UserService;
import dev.samstevens.totp.exceptions.QrGenerationException;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final TotpManager totpManager;

    private final EmailService emailService;

    private final EmailConfirmationTokenRepository emailConfirmationTokenRepository;

    private static final BytesKeyGenerator DEFAULT_TOKEN_GENERATOR = KeyGenerators.secureRandom(15);
    private static final Charset US_ASCII = Charset.forName("US-ASCII");


    @Override
    public MfaTokenData registerUser(User user) throws UserAlreadyExistException, QrGenerationException {
        try{
            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                throw new UserAlreadyExistException("Username already exists");
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            //some additional work
            user.setSecretKey(totpManager.generateSecretKey()); //generating the secret and store with profile
            User savedUser = userRepository.save(user);
            // Create a secure token and send email
            this.sendRegistrationConfirmationEmail(user);
            //Generate the QR Code
            String qrCode = totpManager.getQRCode(savedUser.getSecretKey());
            return MfaTokenData.builder()
                    .mfaCode(savedUser.getSecretKey())
                    .qrCode(qrCode)
                    .build();
        } catch (Exception e){
            throw new MFAServerAppException("Exception while registering the user", e);
        }
    }

    @Override
    public boolean verifyTotp(String code, String email) {
        User user = userRepository.findByEmail(email).get();
        return totpManager.verifyTotp(code, user.getSecretKey());
    }

    @Override
    public void sendRegistrationConfirmationEmail(User user) throws MessagingException {
        // Generate the token
        String tokenValue = new String(Base64.encodeBase64URLSafe(DEFAULT_TOKEN_GENERATOR.generateKey()), US_ASCII);
        EmailConfirmationToken emailConfirmationToken = new EmailConfirmationToken();
        emailConfirmationToken.setToken(tokenValue);
        emailConfirmationToken.setTimeStamp(LocalDateTime.now());
        emailConfirmationToken.setUser(user);
        emailConfirmationTokenRepository.save(emailConfirmationToken);
        // Send email
        emailService.sendConfirmationEmail(emailConfirmationToken);
    }

    @Override
    public boolean verifyUser(String token) throws InvalidTokenException {
        EmailConfirmationToken emailConfirmationToken = emailConfirmationTokenRepository.findByToken(token);
        if(Objects.isNull(emailConfirmationToken) || !token.equals(emailConfirmationToken.getToken())){
            throw new InvalidTokenException("Token is not valid");
        }
        User user = emailConfirmationToken.getUser();
        if (Objects.isNull(user)){
            return false;
        }
        user.setAccountVerified(true);
        userRepository.save(user);
        emailConfirmationTokenRepository.delete(emailConfirmationToken);
        return true;
    }



    @Override
    public UserAddResponse addUser(UserAddRequest user) {
        User user_data = User.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
        userRepository.save(user_data);
        return UserAddResponse.builder()
                .firstname(user_data.getFirstname())
                .lastname(user_data.getLastname())
                .build();
    }

    @Override
    public List<User> findAll() {
        List<User> list = userRepository.findAll();
        List<User> listDTO = new ArrayList<>();

        for (User u : list) {
            listDTO.add(new User(u.getId(), u.getFirstname(), u.getLastname(), u.getEmail(), u.getPassword(), u.isMfaEnabled(), u.getSecretKey(), u.isActive(), u.isAccountVerified()));
        }

        return listDTO;
    }

    @Override
    public Optional<User> findUserById(String userId) {
        return userRepository.findById(userId);
    }
}
