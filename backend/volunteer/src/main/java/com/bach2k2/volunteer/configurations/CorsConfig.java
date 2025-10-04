package com.bach2k2.volunteer.configurations;

import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * Global CORS configuration using WebMvcConfigurer
     * This applies to all endpoints in your application
     */
    // @Override
    // public void addCorsMappings(CorsRegistry registry) {
    // registry.addMapping("/**") // Apply to ALL endpoints, not just /api/**
    // .allowedOrigins(
    // "http://localhost:3000", // React default port
    // "http://localhost:4200", // Angular default port
    // "http://localhost:8081", // Alternative port
    // "http://127.0.0.1:3000",
    // "http://127.0.0.1:4200",
    // "https://your-frontend-domain.com" // Add your production domain
    // )
    // .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD")
    // .allowedHeaders("*")
    // .allowCredentials(true)
    // .maxAge(3600); // Cache preflight response for 1 hour
    // }
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
        .allowedOrigins("*");
    }
    /**
     * CORS Configuration Source Bean for Spring Security integration
     * This is used by Spring Security to handle CORS
     */
    // @Bean
    // public CorsConfigurationSource corsConfigurationSource() {
    // CorsConfiguration configuration = new CorsConfiguration();

    // // Allow specific origins (modify as needed)
    // configuration.setAllowedOrigins(Arrays.asList(
    // "http://localhost:3000",
    // "http://localhost:4200",
    // "http://localhost:8081",
    // "http://127.0.0.1:3000",
    // "http://127.0.0.1:4200",
    // "https://your-frontend-domain.com"
    // ));

    // // Allow all headers
    // configuration.setAllowedHeaders(Arrays.asList("*"));

    // // Allow specific HTTP methods
    // configuration.setAllowedMethods(Arrays.asList(
    // "GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD"
    // ));

    // // Allow credentials (cookies, authorization headers)
    // configuration.setAllowCredentials(true);

    // // Cache preflight response
    // configuration.setMaxAge(3600L);

    // // Apply this configuration to all paths
    // UrlBasedCorsConfigurationSource source = new
    // UrlBasedCorsConfigurationSource();
    // source.registerCorsConfiguration("/**", configuration);

    // return source;
    // }
}