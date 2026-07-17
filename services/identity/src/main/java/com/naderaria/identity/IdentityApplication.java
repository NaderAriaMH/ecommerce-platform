package com.naderaria.identity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
        "com.naderaria.common_core",
        "com.naderaria.common_data",
        "com.naderaria.common_security",
        "com.naderaria.identity"
})
@ConfigurationPropertiesScan({
        "com.naderaria.common_security"
})

public class IdentityApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdentityApplication.class, args);
    }

}
