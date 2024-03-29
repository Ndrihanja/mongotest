package com.ndrianja.mongotest.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ndrianja.mongotest.project.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectAddResponse {
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("status")
    private Status status;

}
