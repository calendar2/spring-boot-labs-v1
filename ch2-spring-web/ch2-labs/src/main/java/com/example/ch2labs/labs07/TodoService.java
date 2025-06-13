package com.example.ch2labs.labs07;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository repository;

    public TodoResponse createTodo(TodoRequest request) {
        manageError(request);
        Todo todo = repository.save(request);
        return convertResponse(todo);
    }

    public List<TodoResponse> allReadTodos() {
        List<Todo> todos = repository.findAll();
        return todos.stream().map(todo -> convertResponse(todo)).collect(Collectors.toList());
    }

    public TodoResponse updateTodo(Long id, TodoRequest request) {
        manageError(request);
        Optional<Todo> todo = Optional.ofNullable(repository.findById(id).orElseThrow(() -> new TodoNotFoundException("해당 ID의 할 일이 존재하지 않습니다.")));
        return convertResponse(repository.update(todo.get().getId(), request));
    }

    public void deleteTodo(Long id) {
        Optional<Todo> todo = Optional.ofNullable(repository.findById(id).orElseThrow(() -> new TodoNotFoundException("해당 ID의 할 일이 존재하지 않습니다.")));
        repository.delete(todo.get().getId());
    }

    private TodoResponse convertResponse(Todo todo) {
        TodoResponse response = new TodoResponse(todo.getId(), todo.getTitle(), todo.getCompleted());
        return response;
    }

    private void manageError(TodoRequest request) {
        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
            throw new InvalidTodoException("제목은 필수입니다!");
        }

        if (request.getCompleted() == null) {
            throw new InvalidTodoException("할 일의 완료 여부를 표시해주세요!");
        }
    }
}
