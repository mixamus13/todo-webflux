package com.apress.todo.todowebflux.repository;

import static java.util.Arrays.asList;
import static reactor.core.publisher.Flux.fromIterable;

import com.apress.todo.todowebflux.domain.ToDo;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author max.dokuchaev
 */
@Repository
public class ToDoRepository {

  private Flux<ToDo> toDoFlux = fromIterable(
      asList(
          new ToDo("Do homework"),
          new ToDo("Workout in the mornings", true),
          new ToDo("Make dinner tonight"),
          new ToDo("Clean the studio", true)));

  public Mono<ToDo> findById(String id) {
    return Mono
        .from(
            toDoFlux.filter(toDo -> toDo.getId().equals(id)));
  }

  public Flux<ToDo> findAll() {
    return toDoFlux;
  }
}
