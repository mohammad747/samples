package com.tiddev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.tiddev.clients")
public class CurrencyConversionApplication {
    public static void main(String[] args) {
        SpringApplication.run(CurrencyConversionApplication.class, args);
    }
}
