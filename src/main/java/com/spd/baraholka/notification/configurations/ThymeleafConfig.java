package com.spd.baraholka.notification.configurations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;


public class ThymeleafConfig {

//    public ThymeleafConfig() {
//    }
//
//    @Bean("resolver")
//    public ITemplateResolver thymeleafTemplateResolver() {
//        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//        templateResolver.setPrefix("templates/");
//        templateResolver.setSuffix(".ftl");
//        templateResolver.setTemplateMode("HTML");
//        templateResolver.setCharacterEncoding("UTF-8");
//        return templateResolver;
//    }
//
//    @Bean
//    public ResourceBundleMessageSource emailMessageSource() {
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasename("mailMessages");
//        return messageSource;
//    }
//
//    @Bean
//    public SpringTemplateEngine thymeleafTemplateEngine(@Qualifier("resolver") ITemplateResolver templateResolver) {
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver);
//        templateEngine.setTemplateEngineMessageSource(emailMessageSource());
//        return templateEngine;
//    }
}
