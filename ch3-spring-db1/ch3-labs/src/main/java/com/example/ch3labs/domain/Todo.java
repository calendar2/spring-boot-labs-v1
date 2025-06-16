package com.example.ch3labs.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class Todo {
    private Long id;
    private String title;
    private Boolean completed;
}
