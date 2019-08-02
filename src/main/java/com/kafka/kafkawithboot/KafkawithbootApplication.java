package com.kafka.kafkawithboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.configuration","com.controller","com.com.model"})


public class KafkawithbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkawithbootApplication.class, args);
    }

}
