package com.ndrianja.mongotest.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAddResponse {
    @JsonProperty("first_name")
    private String firstname;
    @JsonProperty("last_name")
    private String lastname;
}
