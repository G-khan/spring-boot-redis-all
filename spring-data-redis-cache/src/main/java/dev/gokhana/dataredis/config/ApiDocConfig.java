package dev.gokhana.dataredis.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiDocConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Redis Caching with Spring Boot")
                        .description("Redis Caching is part of spring boot redis all in one repository")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Gökhan Ayrancıoğlu")
                                .url("https://gokhana.dev")
                                .email("gokhana@mail.com"))
                        .termsOfService("TOC")
                        .license(new License().name("License").url("#"))
                );
    }

}
