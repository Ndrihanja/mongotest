package com.ndrianja.mongotest.mfa.controller;

import com.ndrianja.mongotest.exception.InvalidTokenException;
import com.ndrianja.mongotest.mfa.model.LoginRequest;
import com.ndrianja.mongotest.mfa.model.MfaVerificationRequest;
import com.ndrianja.mongotest.mfa.model.MfaVerificationResponse;
import com.ndrianja.mongotest.mfa.service.JWTService;
import com.ndrianja.mongotest.user.model.User;
import com.ndrianja.mongotest.user.service.UserService;
import dev.samstevens.totp.exceptions.QrGenerationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JWTService jwtService;
    private final AuthenticationProvider authenticationProvider;
    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated @RequestBody User user) {
        // Register User // Generate QR code using the Secret KEY
        try {
            return ResponseEntity.ok(userService.registerUser(user));
        } catch (QrGenerationException e) {
            return ResponseEntity.internalServerError().body("Something went wrong. Try again.");
        }
    }
    @PostMapping(value="/login", produces = "application/json")
    public ResponseEntity<?> login(@Validated @RequestBody LoginRequest loginRequest) {
        // Validate the user credentials and return the JWT / send redirect to MFA page
        try {//Get the user and Compare the password
            Authentication authentication = authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = (User) authentication.getPrincipal();
            return ResponseEntity.ok(MfaVerificationResponse.builder()
                    .email(loginRequest.getEmail())
                    .tokenValid(Boolean.FALSE)
                    .authValid(Boolean.TRUE)
                    .mfaRequired(Boolean.TRUE)
                    .message("User Authenticated using username and Password")
                    .jwt("")
                    .build());

        } catch (Exception e){
            return ResponseEntity.ok(MfaVerificationResponse.builder()
                    .email(loginRequest.getEmail())
                    .tokenValid(Boolean.FALSE)
                    .authValid(Boolean.FALSE)
                    .mfaRequired(Boolean.FALSE)
                    .message("Invalid Credentials. Please try again.")
                    .jwt("")
                    .build());
        }
    }
    @PostMapping("/verifyTotp")
    public ResponseEntity<?> verifyTotp(@Validated @RequestBody MfaVerificationRequest mfaVerificationRequest) throws ParseException {
        MfaVerificationResponse mfaVerificationResponse = MfaVerificationResponse.builder()
                .email(mfaVerificationRequest.getEmail())
                .tokenValid(Boolean.FALSE)
                .message("Token is not valid please try again!")
                .build();

        // Validate the OTP
        if(userService.verifyTotp(mfaVerificationRequest.getTotp(), mfaVerificationRequest.getEmail())){
            //GENERATE JWT
            mfaVerificationResponse = MfaVerificationResponse.builder()
                    .email(mfaVerificationRequest.getEmail())
                    .tokenValid(Boolean.TRUE)
                    .message("Token is valid")
                    .jwt(jwtService.generateJwt(mfaVerificationRequest.getEmail()))
                    .build();
        }
        return ResponseEntity.ok(mfaVerificationResponse);

    }

    @GetMapping("/confirm-email")
    public ResponseEntity<?> confirmEmail(@RequestParam("token") String token) throws InvalidTokenException {
        try{
            if(userService.verifyUser(token)){
                return ResponseEntity.ok("Your email has been successfully verified.");
            } else {
                return ResponseEntity.ok("User details not found. Please login and regenerate the confirmation link.");
            }
        } catch (InvalidTokenException e){
            return ResponseEntity.ok("Link expired or token already verified.");
        }
    }

}
