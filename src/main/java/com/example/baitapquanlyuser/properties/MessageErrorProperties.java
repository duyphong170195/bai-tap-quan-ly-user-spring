package com.example.baitapquanlyuser.properties;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageErrorProperties implements MessageByLocaleService{

    @Autowired
    private MessageSource messageSource;

    @Override
    public String getData(String id) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(id,null,locale);
    }
}
