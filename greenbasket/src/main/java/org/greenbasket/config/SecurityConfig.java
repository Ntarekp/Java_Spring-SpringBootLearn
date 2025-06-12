package org.greenbasket.config;

import org.greenbasket.security.JwtAuthenticationEntryPoint;
import org.greenbasket.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    // Define our PasswordEncoder bean using BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Expose the AuthenticationManager via AuthenticationConfiguration
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration)
            throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    // Configure the SecurityFilterChain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Disable CSRF because we're using JWT for stateless authentication
        http.csrf(csrf -> csrf.disable());

        // Set up exception handling with our custom unauthorized entry point
        http.exceptionHandling(e -> e.authenticationEntryPoint(unauthorizedHandler));

        // Configure session management to be stateless
        http.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Define URL authorization rules
        http.authorizeHttpRequests(authorize -> authorize
                // Permit authentication endpoints
                .requestMatchers("/api/auth/**").permitAll()
                // Permit Springdoc OpenAPI endpoints
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                // Permit webjar resources (used by Swagger UI)
                .requestMatchers("/webjars/**").permitAll()
                // Permit GET requests to /api/products/**
                .requestMatchers(HttpMethod.GET, "/api/products/**").permitAll()
                // All other requests require authentication
                .anyRequest().authenticated()
        );

        // Add our custom JWT authentication filter before the UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
