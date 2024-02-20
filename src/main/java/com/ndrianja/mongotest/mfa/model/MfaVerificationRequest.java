package com.ndrianja.mongotest.mfa.model;

import lombok.Data;

@Data
public class MfaVerificationRequest {
    private String email;
    private String totp;
}
