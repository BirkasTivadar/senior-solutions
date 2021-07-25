package com.training360.jpaspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JpaspringApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaspringApplication.class, args);
    }

}
