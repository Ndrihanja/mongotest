package com.ndrianja.mongotest.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<User> findAll() {
        List<User> list = userRepository.findAll();
        List<User> listDTO = new ArrayList<>();

        for (User u : list) {
            listDTO.add(new User(u.getId(), u.getFirstname(), u.getLastname(), u.getEmail(), u.getPassword()));
        }

        return listDTO;
    }

    public Optional<User> findUserById(String userId) {
        return userRepository.findById(userId);
    }

}
