package com.bach2k2.volunteer.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Volunteer Management API",
        version = "v1.0",
        description = "REST API for Volunteer Management System",
        contact = @Contact(
            name = "Bach2k2",
            email = "bach2k2@example.com"
        ),
         servers = {
        @Server(url = "http://localhost:8088", description = "Local Development Server"),
        @Server(url = "http://45.117.179.16:8088", description = "Production Server"),
    }
    )
)
@SecurityScheme(
    name = "bearer-authentication",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer",
    description = "Enter JWT Bearer token"
)
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"));
    }
}