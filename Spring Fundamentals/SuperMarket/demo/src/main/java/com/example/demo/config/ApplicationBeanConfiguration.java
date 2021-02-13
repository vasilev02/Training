package com.example.demo.config;

import com.example.demo.util.ValidationUtil;
import com.example.demo.util.ValidationUtilImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Bean
    public ValidationUtil validationUtil() {
        return new ValidationUtilImpl(validator());
    }

}
