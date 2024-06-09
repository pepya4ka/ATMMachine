package com.pepyachka;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BankApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(BankApplication.class)
                .headless(false)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
