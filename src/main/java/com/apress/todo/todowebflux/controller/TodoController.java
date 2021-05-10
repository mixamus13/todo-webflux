package com.apress.todo.todowebflux.controller;

import com.apress.todo.todowebflux.domain.ToDo;
import com.apress.todo.todowebflux.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author max.dokuchaev
 */
@RestController
@RequiredArgsConstructor
public class TodoController {

  private final ToDoRepository repository;

  @GetMapping("/todo/{id}")
  public Mono<ToDo> getToDo(@PathVariable String id) {
    return repository.findById(id);
  }

  @GetMapping("/todo")
  public Flux<ToDo> getToDos() {
    return this.repository.findAll();
  }
}