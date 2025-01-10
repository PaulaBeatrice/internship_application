package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "repository")
@EntityScan(basePackages = "model")
@ComponentScan(basePackages = {"controller", "repository", "model", "service.implementation", "security"})
public class InternshipApplicationApp {

    public static void main(String[] args) {
        SpringApplication.run(InternshipApplicationApp.class, args);
    }

}


