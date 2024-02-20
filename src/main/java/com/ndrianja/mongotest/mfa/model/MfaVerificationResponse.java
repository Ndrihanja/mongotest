package com.ndrianja.mongotest.mfa.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MfaVerificationResponse {
    private String email;
    private String jwt;
    private boolean mfaRequired;
    private boolean authValid;
    private boolean tokenValid;
    private String message;
}
