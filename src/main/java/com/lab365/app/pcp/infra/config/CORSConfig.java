package com.lab365.app.pcp.infra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Applies to all endpoints
                .allowedOrigins("http://localhost:4200", "http://127.0.0.1:4200/") // Allows requests only from this
                                                                                   // origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allows these HTTP methods
                .allowedHeaders("*") // Allows all headers
                .allowCredentials(true); // Allows cookies if necessary
    }
}
