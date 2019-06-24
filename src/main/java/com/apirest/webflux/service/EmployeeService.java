package com.apirest.webflux.service;

import com.apirest.webflux.model.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * EmployeeService
 */
public interface EmployeeService {

    void create(Employee e);

    Mono<Employee> findById(String id);

    Flux<Employee> findByName(String name);

    Flux<Employee> findAll();

    Mono<Employee> update(Employee e);

    Mono<Void> delete(String id);

    Flux<Employee> stream(String id);
}