package com.apirest.webflux.configuration;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/**
 * MongoConfig
 */
@Configuration
@EnableReactiveMongoRepositories(basePackages = {"com.apirest.webflux.repository"})
public class MongoConfig extends AbstractReactiveMongoConfiguration {

    @Value("${env.mongo.dbName}")
    private String dbName;

    @Value("${env.mongo.port}")
    private String port;

    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create();
    }

    @Override
    protected String getDatabaseName() {
        return this.dbName;
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
    }

    /**
     * @return the port
     */
    public String getPort() {
        return port;
    }

    /**
     * @return the dbName
     */
    public String getDbName() {
        return this.dbName;
    }
}