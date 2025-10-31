package com.senai.conta_bancaria.aplication.infrastructure.config;

import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI oficinaOpenAPI() {
        return new OpenAPI()
                .info(new ProcessHandle.Info()
                        .title("API - Conta Bancária")
                        .description("Cadastro e gestão de serviços de uma oficina.")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Equipe Oficina")
                                .email("suporte@oficina.com"))
                );
    }

    private SecurityScheme createAPIKeyScheme(){
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}
