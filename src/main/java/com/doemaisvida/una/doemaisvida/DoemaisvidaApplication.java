package com.doemaisvida.una.doemaisvida;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "doe mais vida", version = "1", description = "Api para site de doação de sangue"))
public class DoemaisvidaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoemaisvidaApplication.class, args);
	}

}
