package com.apirest.webflux.controller;

import com.apirest.webflux.model.Employee;
import com.apirest.webflux.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

/**
 * RouteHandles
 * 
 * Netty Reactive - Identificador de Rotas reativa
 */
@Component
public class RouteHandles {

    @Autowired
    private EmployeeService empService;

    public Mono<ServerResponse> findAll(ServerRequest serverRequest) {
        return ServerResponse.ok().body(empService.findAll(), Employee.class)
                .doOnError(throwable -> new IllegalStateException("a"));
    }

    public Mono<ServerResponse> byId(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok().body(empService.findById(id), Employee.class)
                .doOnError(throwable -> new IllegalStateException("b"));
    }

    public Mono<ServerResponse> events(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok()
            .contentType(MediaType.TEXT_EVENT_STREAM)
            .body(empService.stream(id),Employee.class)
            .doOnError(throwable -> new IllegalStateException("c"));
    }
}