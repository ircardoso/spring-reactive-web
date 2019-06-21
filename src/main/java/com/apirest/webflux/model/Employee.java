package com.apirest.webflux.model;

import org.bson.types.ObjectId;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Employee
 */
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Document
public class Employee {

    @Id
    private ObjectId id;
    private String name;
    private long salary;


    public Employee() {
    }

    public Employee(ObjectId id, String name, long salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public ObjectId getId() {
        return this.id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSalary() {
        return this.salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public Employee id(ObjectId id) {
        this.id = id;
        return this;
    }

    public Employee name(String name) {
        this.name = name;
        return this;
    }

    public Employee salary(long salary) {
        this.salary = salary;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", salary='" + getSalary() + "'" +
            "}";
    }
    

}