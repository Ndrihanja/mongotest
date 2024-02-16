package com.ndrianja.mongotest.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserAddResponse addUser(UserAddRequest user) {
        User user_data = User.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
        userRepository.save(user_data);
        return UserAddResponse.builder()
                .firstname(user_data.getFirstname())
                .lastname(user_data.getLastname())
                .build();

    }

}
