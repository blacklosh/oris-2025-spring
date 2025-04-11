package ru.itis.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Properties;

@RequiredArgsConstructor
@Configuration
@ComponentScan(basePackages = "ru.itis")
@PropertySource(value = "classpath:application.yaml", factory = YamlPropertySourceFactory.class)
public class ApplicationConfig {

    private final Environment environment;

    @Value("${freemarker.path}")
    private String freemarkerConfigurerTemplateLoaderPath;

    @Bean
    public ViewResolver viewResolver() {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setPrefix(environment.getProperty("view-resolver.prefix"));
        viewResolver.setSuffix(environment.getProperty("view-resolver.suffix"));
        viewResolver.setContentType("text/html;charset=UTF-8");
        viewResolver.setExposeSpringMacroHelpers(true);
        return viewResolver;
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath(freemarkerConfigurerTemplateLoaderPath);

        Properties properties = new Properties();
        properties.put("auto_import", "\"/spring.ftl\" as spring");

        configurer.setFreemarkerSettings(properties);
        return configurer;
    }
}
