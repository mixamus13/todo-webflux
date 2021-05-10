package com.apress.todo.todowebflux.reactive;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;

import com.apress.todo.todowebflux.domain.ToDo;
import com.apress.todo.todowebflux.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author max.dokuchaev
 */
@Component
@RequiredArgsConstructor
public class ToDoHandler {

  private final ToDoRepository repository;

  public Mono<ServerResponse> getToDo(ServerRequest request) {
    String toDoId = request.pathVariable("id");
    Mono<ServerResponse> notFound = notFound().build();
    Mono<ToDo> toDo = repository.findById(toDoId);
    return toDo
        .flatMap(t ->
            ServerResponse
                .ok()
                .contentType(APPLICATION_JSON)
                .body(fromValue(t)))
        .switchIfEmpty(notFound);
  }

  public Mono<ServerResponse> getToDos(ServerRequest request) {
    Flux<ToDo> toDos = repository.findAll();
    return ServerResponse
        .ok()
        .contentType(APPLICATION_JSON)
        .body(toDos, ToDo.class);
  }
}
