package com.captainyun7.postappwithsecurity.service;

import com.captainyun7.postappwithsecurity.dto.user.SignUpRequest;
import com.captainyun7.postappwithsecurity.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse register(SignUpRequest request) {
        // 사용자 이름 중복 검사
        if (userService.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username is already in use");
        }

        // 비밀번호 암호화
        request.setPassword(passwordEncoder.encode(request.getPassword()));

        return userService.createUser(request);
    }
}
