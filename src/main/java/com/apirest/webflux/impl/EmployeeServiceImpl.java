package com.apirest.webflux.impl;

import com.apirest.webflux.model.Employee;
import com.apirest.webflux.repository.EmployeeRespository;
import com.apirest.webflux.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * EmployeeServiceImpl
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRespository employeeRepo;

    @Autowired
    private MongoOperations op;

    @Override
    public void create(Employee e) {
        employeeRepo.save(e).subscribe();
    }

    @Override
    public Mono<Employee> findById(Integer id) {
        return employeeRepo.findById(id);
    }

    @Override
    public Flux<Employee> findByName(String name) {
        return employeeRepo.findByName(name);
    }

    @Override
    public Flux<Employee> findAll() {
        return employeeRepo.findAll();
    }

    @Override
    public Mono<Employee> update(Employee e) {
        return employeeRepo.save(e);
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return employeeRepo.deleteById(id);
    }
}