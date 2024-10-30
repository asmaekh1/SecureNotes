package com.secure.notes.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) ->
                    requests
                            .requestMatchers("/contact").permitAll()   // allow user to access the landing page without authentification
                            .requestMatchers("/public/**").permitAll()  // allow any endpoint start with public
                            .requestMatchers("/admin").denyAll()  // deny any acess to this endpoint used when we have a page that is under maintenance
                            .anyRequest().authenticated());// any request must be authentificated

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        http.formLogin(Customizer.withDefaults());  // il permet d injecter un formulaire de login et logout
        http.httpBasic(Customizer.withDefaults());  // il permet d avoir que un popup javascript qui permet de s authentifier
        return (SecurityFilterChain)http.build();
    }
}
