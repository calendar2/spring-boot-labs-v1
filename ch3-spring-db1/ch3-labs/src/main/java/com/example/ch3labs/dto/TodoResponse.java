package com.example.ch3labs.dto;

import com.example.ch3labs.domain.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoResponse {
    private Long id;
    private String title;
    private Boolean completed;

    public static TodoResponse from(Todo todo) {
        return new TodoResponse(todo.getId(), todo.getTitle(), todo.getCompleted());
    }
}
