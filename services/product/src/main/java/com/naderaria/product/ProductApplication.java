package com.naderaria.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
        "com.naderaria.common_core",
        "com.naderaria.common_data",
        "com.naderaria.common_security",
        "com.naderaria.product"
})
@ConfigurationPropertiesScan({
        "com.naderaria.common_security"
})
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

}
