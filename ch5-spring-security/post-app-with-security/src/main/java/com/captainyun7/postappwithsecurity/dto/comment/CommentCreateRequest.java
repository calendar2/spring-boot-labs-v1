package com.captainyun7.postappwithsecurity.dto.comment;

import com.captainyun7.postappwithsecurity.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreateRequest {
    private String content;
    private Long parentId;
}
