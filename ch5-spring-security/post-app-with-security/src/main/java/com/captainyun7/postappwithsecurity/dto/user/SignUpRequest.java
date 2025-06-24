package com.captainyun7.postappwithsecurity.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    @NotBlank(message = "사용자 이름은 필수입니다")
    @Size(min = 4, max = 20, message = "사용자 이름은 4-20자 사이여야 합니다")
    private String username;

    @NotBlank(message = "비밀번호는 필수입니다")
    @Size(min = 6, message = "비밀번호는 최소 6자 이상이어야 합니다")
    private String password;

    @NotBlank(message = "역할은 필수입니다")
    private String role;
}
