package com.ndrianja.mongotest.mfa.model;

import lombok.Data;

@Data
public class LoginRequest {
    String email;
    String password;
}
