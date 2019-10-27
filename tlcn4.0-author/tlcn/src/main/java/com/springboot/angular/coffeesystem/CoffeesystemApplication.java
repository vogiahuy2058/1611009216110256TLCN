package com.springboot.angular.coffeesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories("com.springboot.angular.coffeesystem")
@EnableSwagger2
public class CoffeesystemApplication {

    @Bean
    public Docket API() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.springboot.angular.coffeesystem"))
                .paths(PathSelectors.regex("/.*"))
                .build();
    }
    public static void main(String[] args) {
        SpringApplication.run(CoffeesystemApplication.class, args);
    }

}
