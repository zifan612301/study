package com.hq.secondhand_book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.hq.secondhand_book.*")
public class SecondhandBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondhandBookApplication.class, args);
    }
}
