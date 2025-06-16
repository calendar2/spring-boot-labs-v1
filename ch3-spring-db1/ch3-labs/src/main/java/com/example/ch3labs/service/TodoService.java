package com.example.ch3labs.service;

import com.example.ch3labs.domain.Todo;
import com.example.ch3labs.dto.TodoCreateRequest;
import com.example.ch3labs.dto.TodoResponse;
import com.example.ch3labs.dto.TodoUpdateRequest;
import com.example.ch3labs.exception.TodoNotFoundException;
import com.example.ch3labs.mapper.TodoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoMapper todoMapper;

    public TodoResponse createTodo(TodoCreateRequest request) {
        request.validate();
        Todo todo = request.toDomain();
        todoMapper.save(todo);

        return TodoResponse.from(todo);
    }

    public List<TodoResponse> allReadTodos() {
        List<Todo> todos = todoMapper.findAll();
        return todos.stream().map(todo -> TodoResponse.from(todo)).collect(Collectors.toList());
    }

    public TodoResponse updateTodo(Long id, TodoUpdateRequest request) {
        request.validate();

        Todo todo = getTodoOrThrow(id);
        request.updateEntity(todo);

        int updated = todoMapper.update(todo);
        if (updated == 0) {
            throw new RuntimeException("게시글 수정 중에 문제가 발생했습니다.");
        }

        return TodoResponse.from(todo);
    }

    public void deleteTodo(Long id) {
        getTodoOrThrow(id);
        int deleted = todoMapper.delete(id);
        if (deleted == 0) {
            throw new RuntimeException("게시글 삭제 중에 문제가 발생했습니다.");
        }
    }

    private Todo getTodoOrThrow(Long id) {
        return todoMapper.findById(id).orElseThrow(() -> new TodoNotFoundException("해당 ID의 할 일이 존재하지 않습니다."));
    }
}
