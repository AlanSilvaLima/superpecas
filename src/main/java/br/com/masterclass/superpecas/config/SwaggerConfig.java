package br.com.masterclass.superpecas.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class SwaggerConfig {
    @Bean
    public GroupedOpenApi carroApi() {
        return GroupedOpenApi.builder()
                .group("Carro")
                .pathsToMatch("/carro/**")
                .build();
    }

    @Bean
    public GroupedOpenApi pecaApi() {
        return GroupedOpenApi.builder()
                .group("Peca")
                .pathsToMatch("/peca/**")
                .build();
    }
}
