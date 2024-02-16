package com.ndrianja.mongotest.project;

import com.ndrianja.mongotest.user.User;

import java.util.List;

public class Project {

    private String id;
    private String description;
    private Status status;

    private List<User> members;
}
