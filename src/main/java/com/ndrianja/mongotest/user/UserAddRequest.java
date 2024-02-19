package com.ndrianja.mongotest.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAddRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
