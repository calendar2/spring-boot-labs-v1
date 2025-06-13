package com.example.ch2labs.labs07;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TodoRepository {
    private Map<Long, Todo> store = new HashMap<>();
    private Long sequence = 1L;

    // 할 일 작성
    public Todo save(TodoRequest request) {
        Todo todo = new Todo();
        todo.setId(sequence++);
        todo.setTitle(request.getTitle());
        todo.setCompleted(request.getCompleted());
        store.put(todo.getId(), todo);

        return todo;
    }

    // 전체 목록 조회
    public List<Todo> findAll() {
        List<Todo> todos = new ArrayList<>();
        todos.addAll(store.values());

        return todos;
    }

    // 단일 조회
    public Optional<Todo> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    // 수정
    public Todo update(Long id, TodoRequest request) {
        Todo todo = store.get(id);
        todo.setTitle(request.getTitle());
        todo.setCompleted(request.getCompleted());

        return todo;
    }

    // 삭제
    public void delete(Long id) {
        store.remove(id);
    }
}
