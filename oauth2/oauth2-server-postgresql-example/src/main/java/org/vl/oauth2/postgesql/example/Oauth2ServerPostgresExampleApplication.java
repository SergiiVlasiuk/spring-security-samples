package org.vl.oauth2.postgesql.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan
public class Oauth2ServerPostgresExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2ServerPostgresExampleApplication.class, args);
    }
}
