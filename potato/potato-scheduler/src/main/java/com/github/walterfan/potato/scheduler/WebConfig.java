package com.github.walterfan.potato.scheduler;

import com.google.common.base.Predicates;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;



@Configuration
@EnableAutoConfiguration
@EnableSwagger2
@ComponentScan(basePackages = {
        "com.github.walterfan.potato.scheduler",
        "com.github.walterfan.potato.common"})
@PropertySource("classpath:application.properties")
public class WebConfig {

    private boolean enableSwagger = true;
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .forCodeGeneration(Boolean.TRUE)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.github.walterfan.potato.scheduler"))
                //.paths(regex("/potato/api/v1/*"))
                .paths(PathSelectors.any())
                .paths(Predicates.and(PathSelectors.regex("/scheduler/api.*")))
                .build()
                .enable(enableSwagger)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Potato Scheduler REST API",
                "Potato Scheduler REST description of API.",
                "v1",
                "Terms of service",
                new Contact("Walter Fan", "http://www.fanyamin.com", "walter.fan@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }

}