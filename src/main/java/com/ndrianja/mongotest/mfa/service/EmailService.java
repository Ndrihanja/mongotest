package com.ndrianja.mongotest.mfa.service;

import com.ndrianja.mongotest.mfa.model.EmailConfirmationToken;
import jakarta.mail.MessagingException;
public interface EmailService {
    void sendConfirmationEmail(EmailConfirmationToken emailConfirmationToken) throws MessagingException;
}
