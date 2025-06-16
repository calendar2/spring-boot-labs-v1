package com.example.ch3labs.controller;

import com.example.ch3labs.dto.TodoCreateRequest;
import com.example.ch3labs.dto.TodoResponse;
import com.example.ch3labs.dto.TodoUpdateRequest;
import com.example.ch3labs.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService service;

    @PostMapping("/todos")
    public ResponseEntity<TodoResponse> createTodo(@RequestBody TodoCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createTodo(request));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TodoResponse>> readTodos() {
        return ResponseEntity.ok(service.allReadTodos());
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<TodoResponse> updateTodo(@PathVariable Long id, @RequestBody TodoUpdateRequest request) {
        return ResponseEntity.ok(service.updateTodo(id, request));
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        service.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}
