package com.apirest.webflux.configuration;

import com.apirest.webflux.controller.RouteHandles;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

/**
 * RoutesConfiguration
 */
@Configuration
public class RoutesConfiguration {

    @Bean
    RouterFunction<?> routes(RouteHandles routeHandles) {
        return RouterFunctions.route(RequestPredicates.GET("/emp"), routeHandles::findAll)
                .andRoute(RequestPredicates.GET("/emp/{id}"), routeHandles::byId)
                .andRoute(RequestPredicates.GET("/emp/event/{id}"), routeHandles::events);
    }

}