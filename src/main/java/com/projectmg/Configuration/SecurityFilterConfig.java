package com.projectmg.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityFilterConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable()) // necessário para JSON
                .cors(cors -> {}) // usa seu WebMvcConfig
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // opcional
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/mg/usuario/login",
                                "/mg/usuario/cadastrar"
                        ).permitAll()
                        .anyRequest().permitAll() // enquanto não cria token, deixa tudo liberado
                );

        return http.build();
    }
}
