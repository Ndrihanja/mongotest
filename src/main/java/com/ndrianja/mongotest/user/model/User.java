package com.ndrianja.mongotest.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    @Indexed
    @NotBlank
    private String email;
    private String password;
    boolean mfaEnabled;
    @JsonIgnore
    private String secretKey;
    private boolean active;
    private boolean accountVerified;



}
