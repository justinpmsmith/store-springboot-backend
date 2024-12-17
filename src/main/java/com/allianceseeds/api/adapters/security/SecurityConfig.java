package com.allianceseeds.api.adapters.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {

    public static final String ADMIN = "app-admin";
    private final JwtConverter jwtConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable); // Disable CSRF protection
        http.authorizeHttpRequests(authz -> authz
                // Allow all GET and POST requests to /api/v1/client/** without authentication
                .requestMatchers(HttpMethod.POST, "/client/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/client/**").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/client/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/admin/**").hasRole(ADMIN)
                .requestMatchers(HttpMethod.POST, "/admin/**").hasRole(ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/admin/**").hasRole(ADMIN)

                // Ensure other requests are authenticated
                .anyRequest().authenticated());

        http.sessionManagement(sess -> sess.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS));
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtConverter)));

        return http.build();
    }
}
