package com.example.ch2labs.labs07;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class Controller {
    private final TodoService service;

    @PostMapping("/todos")
    public ResponseEntity<TodoResponse> createTodo(@RequestBody TodoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createTodo(request));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TodoResponse>> readTodos() {
        return ResponseEntity.ok(service.allReadTodos());
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<TodoResponse> updateTodo(@PathVariable Long id, @RequestBody TodoRequest request) {
        return ResponseEntity.ok(service.updateTodo(id, request));
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<TodoResponse> deleteTodo(@PathVariable Long id) {
        service.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}
