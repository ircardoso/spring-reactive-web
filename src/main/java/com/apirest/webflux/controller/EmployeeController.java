package com.apirest.webflux.controller;

import com.apirest.webflux.impl.EmployeeServiceImpl;
import com.apirest.webflux.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * EmployeeController
 */
@RestController
@RequestMapping(path = EmployeeController.EMPLOYEE)
public class EmployeeController {

    static final String EMPLOYEE = "/employee";
    static final String APPLICATION_JSON = "application/json";

    @Autowired
    private EmployeeServiceImpl employeeService;

    @PostMapping(path = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Employee e) {
        employeeService.create(e);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Mono<Employee>> findById(@PathVariable("id") String id) {
        Mono<Employee> e = employeeService.findById(id);
        HttpStatus status = e != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<Employee>>(e, status);
    }

    @GetMapping(path = "/name/{name}")
    public Flux<Employee> findByName(@PathVariable("name") String name) {
        return employeeService.findByName(name);
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping(path = "/events/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Employee> eventsOfStream(@PathVariable("id") String id) {
        return employeeService.stream(id);
    }

    @PutMapping(path = "/value")
    public Mono<Employee> update(@RequestBody Employee e) {
        return employeeService.update(e);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void delete(@PathVariable("id") String id) {
        employeeService.delete(id).subscribe();
    }
}