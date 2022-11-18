package com.senai.copadomundo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CopadomundoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CopadomundoApplication.class, args);
    }

    @Bean
    public ModelMapper run(){
        return new ModelMapper();
    }

}
