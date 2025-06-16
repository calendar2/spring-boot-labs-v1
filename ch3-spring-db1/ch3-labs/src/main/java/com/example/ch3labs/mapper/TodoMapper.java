package com.example.ch3labs.mapper;

import com.example.ch3labs.domain.Todo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TodoMapper {
    void save(Todo todo);
    List<Todo> findAll();
    Optional<Todo> findById(Long id);
    int update(Todo todo);
    int delete(Long id);
}
