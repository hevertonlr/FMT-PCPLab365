package com.lab365.app.pcp.infra.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

        @Bean
        OpenAPI openAPI() {
                return new OpenAPI()
                                .info(new Info().title("PCPLab365 API").version("1.0"))
                                .components(new Components()
                                                .addSecuritySchemes("Bearer JWT",
                                                                new SecurityScheme()
                                                                                .type(Type.HTTP)
                                                                                .scheme("bearer")
                                                                                .bearerFormat("JWT")
                                                                                .in(In.HEADER)
                                                                                .name("Authorization")))
                                .addSecurityItem(new SecurityRequirement().addList("Bearer JWT"));
        }

}
