package com.naderaria.common_core.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageConfiguration {

    @Bean
    public MessageSource messageSource() {

        ReloadableResourceBundleMessageSource source =
                new ReloadableResourceBundleMessageSource();

        source.setBasename("classpath:messages");
        source.setDefaultEncoding("UTF-8");

        return source;
    }
}
