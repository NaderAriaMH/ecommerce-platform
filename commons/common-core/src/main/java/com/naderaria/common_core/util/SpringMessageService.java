package com.naderaria.common_core.util;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;


@Component
@AllArgsConstructor
public class SpringMessageService implements MessageService {

    private final MessageSource messageSource;

    public String getLocalizedMessage(String translationKey) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(translationKey, null, locale);
    }



}