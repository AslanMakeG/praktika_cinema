package com.praktika.cinema.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SwaggerConfig {

    @Bean
    public OpenAPI api(){
        return new OpenAPI()
                .servers(
                        List.of(new Server().url("http://localhost:8080"))
                )
                .components(new Components()
                        .addSecuritySchemes("Authorization", new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization").description("Чтобы авторизоваться напишите: Bearer *ваш JWT без звездочек* ")))
                // AddSecurityItem section applies created scheme globally
                .addSecurityItem(new SecurityRequirement().addList("Authorization"))
                .info(
                        new Info().title("Film API").description("API для взаимодействия с фильмами")
                );
    }
}
