package com.apirest.webflux.impl;

import java.time.Duration;
import java.util.stream.Stream;

import com.apirest.webflux.model.Employee;
import com.apirest.webflux.repository.EmployeeRespository;
import com.apirest.webflux.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

/**
 * EmployeeServiceImpl
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRespository employeeRepo;

    @Override
    public void create(Employee e) {
        employeeRepo.save(e).subscribe();
    }

    @Override
    public Mono<Employee> findById(String id) {
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
    public Mono<Void> delete(String id) {
        return employeeRepo.deleteById(id);
    }

    @Override
    public Flux<Employee> stream(String id) {
        return employeeRepo.findById(id).flatMapMany(emp -> {
            Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
            Flux<Employee> events = Flux
                    .fromStream(Stream.generate(() -> new Employee(emp.getName(), emp.getSalary())));
            return Flux.zip(interval, events).map(Tuple2::getT2);
        });
    }
}