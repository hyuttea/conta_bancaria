package com.senai.conta_bancaria.aplication.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final UsuarioDetailsService usuarioDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(
                        AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**","/swagger-ui/**","/v3/api-docs/**", "/index.html", "/style.css", "/script.js", "/favicon.ico").permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/clientes", "/api/taxa").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/clientes", "/api/conta/**", "/api/taxa/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/clientes/**", "/api/conta/**", "/api/taxa/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/conta/**", "/api/taxa/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/conta", "/api/pagamento").hasAnyRole("ADMIN", "CLIENTE")
                        .requestMatchers(HttpMethod.GET, "/api/conta/numero/**", "/api/pagamento/**").hasAnyRole("ADMIN", "CLIENTE")


                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .userDetailsService(usuarioDetailsService);

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
