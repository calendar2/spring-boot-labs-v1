package com.example.ch3labs.dto;

import com.example.ch3labs.domain.Todo;
import com.example.ch3labs.exception.InvalidTodoException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoUpdateRequest {
    private String title;
    private Boolean completed;

    public void validate() {
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidTodoException("제목은 필수입니다!");
        }

        if (completed == null) {
            throw new InvalidTodoException("완료 여부를 표시해주세요!");
        }
    }

    public void updateEntity(Todo todo) {
        todo.setTitle(this.title);
        todo.setCompleted(this.completed);
    }
}
