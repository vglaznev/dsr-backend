package com.vglaznev.shorturlservice.config;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.function.Supplier;

@Configuration
public class Config {
    @Bean
    public Supplier<String> currentLocation() {
        return () -> ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
    }

    @Bean
    public UrlValidator urlValidator() {
        return new UrlValidator();
    }

}
