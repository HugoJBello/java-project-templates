package com.ricoh.igae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class FirmaTestApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FirmaTestApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(FirmaTestApplication.class, args);
    }

}