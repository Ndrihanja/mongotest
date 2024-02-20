package com.ndrianja.mongotest.mfa.model;

import com.ndrianja.mongotest.user.model.User;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class EmailConfirmationToken {
    @Id
    private String id;
    @Indexed
    private String token;
    @CreatedDate
    @ReadOnlyProperty
    private LocalDateTime timeStamp;
    @DBRef
    private User user;
}
