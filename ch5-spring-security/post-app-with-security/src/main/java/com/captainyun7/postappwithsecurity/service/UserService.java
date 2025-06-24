package com.captainyun7.postappwithsecurity.service;

import com.captainyun7.postappwithsecurity.domain.User;
import com.captainyun7.postappwithsecurity.dto.user.SignUpRequest;
import com.captainyun7.postappwithsecurity.dto.user.UserResponse;
import com.captainyun7.postappwithsecurity.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponse createUser(SignUpRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .role(request.getRole())
                .build();

        User savedUser = userRepository.save(user);

        return UserResponse.fromEntity(savedUser);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
