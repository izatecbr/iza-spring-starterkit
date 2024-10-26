package com.iza.core.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.request.RequestContextListener;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private JwtFilter filter;

    private static final String[] SWAGGER_WHITELIST = {
            "/v2/api-docs/**",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrf -> csrf.disable())
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(SWAGGER_WHITELIST).permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                        //.requestMatchers("/customers/**").permitAll()
                        //.requestMatchers("/products/**").permitAll()

                        .requestMatchers(HttpMethod.POST,"/customers").hasAnyRole("CUSTOMERS_FULL","CUSTOMERS_INSERT")
                        .requestMatchers(HttpMethod.PUT,"/customers/**").hasAnyRole("CUSTOMERS_FULL","CUSTOMERS_UPDATE")
                        .requestMatchers(HttpMethod.GET,"/customers").hasAnyRole("CUSTOMERS_FULL","CUSTOMERS_SEARCH")
                        .requestMatchers(HttpMethod.GET,"/customers/**").hasAnyRole("CUSTOMERS_FULL","CUSTOMERS_FIND")

                        .requestMatchers(HttpMethod.POST,"/products").hasAnyRole("PRODUCTS_FULL","PRODUCTS_INSERT")
                        .requestMatchers(HttpMethod.PUT,"/products/**").hasAnyRole("PRODUCTS_FULL","PRODUCTS_UPDATE")
                        .requestMatchers(HttpMethod.GET,"/products").hasAnyRole("PRODUCTS_FULL","PRODUCTS_SEARCH")
                        .requestMatchers(HttpMethod.GET,"/products/**").hasAnyRole("PRODUCTS_FULL","PRODUCTS_FIND")


                        .anyRequest().authenticated()
                ).addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
}