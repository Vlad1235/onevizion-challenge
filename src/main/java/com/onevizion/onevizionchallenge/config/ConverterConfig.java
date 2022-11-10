package com.onevizion.onevizionchallenge.config;

import com.onevizion.onevizionchallenge.mapper.BookConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConverterConfig implements WebMvcConfigurer {

    @Bean(name = "conversionService")
    public ConversionService conversionService() {
        DefaultConversionService service = new DefaultConversionService();
        service.addConverter(new BookConverter());
        return service;
    }


}
