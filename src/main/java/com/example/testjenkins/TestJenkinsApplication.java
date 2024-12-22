package com.example.testjenkins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestJenkinsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestJenkinsApplication.class, args);
        System.out.println("Hello world!");
        System.out.println(System.currentTimeMillis());
    }

}
