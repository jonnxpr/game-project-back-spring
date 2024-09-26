package com.games.gameproject.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {
        // Configuração do servidor
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development");

        // Informações de contato
        Contact myContact = new Contact();
        myContact.setName("Jonathan Douglas Diego Tavares");
        // myContact.setEmail("y@gmail.com");

        // Informações gerais da API
        Info information = new Info()
                .title("Gamepedia API")
                .version("1.0.0")
                .description("This API exposes endpoints to manage games.")
                .contact(myContact);

        // Retorna a configuração do OpenAPI
        return new OpenAPI()
                .info(information)
                .servers(List.of(server));
    }
}