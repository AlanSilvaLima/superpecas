package br.com.masterclass.superpecas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableJpaRepositories(basePackages = "br.com.masterclass.superpecas.repository")

@EntityScan(basePackages={"br.com.masterclass.superpecas.model"})

@SpringBootApplication(scanBasePackages = "br.com.masterclass.superpecas.*")

@ComponentScan({"br.com.masterclass.superpecas.*"})
public class SuperPecasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperPecasApplication.class, args);
	}

}
