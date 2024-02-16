package com.ndrianja.mongotest.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@Builder
public class User {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;



}
