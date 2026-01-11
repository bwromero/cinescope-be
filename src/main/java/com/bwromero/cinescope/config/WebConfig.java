package com.bwromero.cinescope.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Allow all API endpoints
                .allowedOrigins("http://localhost:4200",
                                "https://cinescope-7570yghl1-bryanromerodev-gmailcoms-projects.vercel.app",
                                "https://*.vercel.app" ) // Angular default port
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
