package com.mlk.criteria.web;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("locale")
@RequiredArgsConstructor
public class LocaleDemoController {

    private final MessageSource messageSource;

    @GetMapping
    public String sayHello() {
        System.err.println(LocaleContextHolder.getLocale());
        return messageSource.getMessage("common.hello", null, LocaleContextHolder.getLocale());
    }

    @GetMapping("/with-header")
    public String sayHelloWithHeaders(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        System.err.println(LocaleContextHolder.getLocale());
        System.err.println(locale);
        return messageSource.getMessage("common.hello", null, LocaleContextHolder.getLocale());
    }
}