package com.example.wellington.udemy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {


        //via EXTENSION _.xml _.JSON Deprecated on Spring Boot 2.6
       /**  // Via QUERY PARAM http://localhost:8080/api/v1/2?mediatype=xml
       configurer.favorParameter(true).parameterName("mediaType")
                .ignoreAcceptHeader(true).useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML);*/
    // Via QUERY PARAM http://localhost:8080/api/v1/2?mediatype=xml

       configurer.favorParameter(false)
                .ignoreAcceptHeader(false)
               .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("yaml", MediaType.APPLICATION_YAML);
    }
}
