package org.example.config;

import org.example.validation.impl.EmailPatternValidatorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "org.example")
public class SpringProjectConfig {

    @Bean
    public EmailPatternValidatorImpl emailPatternValidator() {
        return new EmailPatternValidatorImpl("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }

}
