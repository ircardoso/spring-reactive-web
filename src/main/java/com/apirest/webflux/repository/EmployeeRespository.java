package com.apirest.webflux.repository;

import com.apirest.webflux.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * EmployeeRespository
 */
@Repository
public interface EmployeeRespository extends ReactiveMongoRepository<Employee, String> {

    // default Flux<Employee> teste() {
    //     Sort s = new Sort(Sort.Direction.DESC, "id");
    //     Flux<Employee> e = this.findAll(s);

    //     e.collectSortedList().block().forEach(System.out::println);

    //     Integer a = e != null && e.collectList().block() != null ? e.collectList().block().get(0).getId() : 0;

    //     System.out.println("IDDD -> " + a);

    //     return this.findAll(new Sort(Sort.Direction.DESC, "id"));
    // }

    // @Override
    // default <S extends Employee> Mono<S> save(S entity) {
        
    //     this.insert(entity)
    //         .flatMap(p -> mongoOps.findOne(new Query(where("id").is("id")), entity.getClass())
    //         .)

    // }

    @Query("{ 'name' : ?0 } ")
    Flux<Employee> findByName(final String name);
}