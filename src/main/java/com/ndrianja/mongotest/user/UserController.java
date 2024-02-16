package com.ndrianja.mongotest.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/create")
    public ResponseEntity<UserAddResponse> addUser(@RequestBody UserAddRequest user) {

        return ResponseEntity.ok(userService.addUser(user));

    }
}
