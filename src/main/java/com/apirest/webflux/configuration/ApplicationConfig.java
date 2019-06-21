package com.apirest.webflux.configuration;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * ApplicationConfig
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public static PropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer(){
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setLocation(new ClassPathResource("application.yml"));
        ppc.setIgnoreUnresolvablePlaceholders(true);
        return ppc;
    }
    
}