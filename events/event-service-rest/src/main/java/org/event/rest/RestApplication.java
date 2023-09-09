package org.event.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.event", "org.event.api", "org.event.api.repository"})
public class RestApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class,args);
    }
}
