package com.secure.notes.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
        http.csrf(AbstractHttpConfigurer::disable);
        //http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }
}

/*public class SecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) ->
                    requests
                           // .requestMatchers("/contact").permitAll()   // allow user to access the landing page without authentification
                           .requestMatchers("/api/notes/**").permitAll()  // allow any endpoint start with public
                           // .requestMatchers("/admin").denyAll()  // deny any acess to this endpoint used when we have a page that is under maintenance
                            .anyRequest().authenticated());// any request must be authentificated
                            http.csrf(AbstractHttpConfigurer::disable);

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        http.formLogin(Customizer.withDefaults());  // il permet d injecter un formulaire de login et logout
        http.httpBasic(Customizer.withDefaults());  // il permet d avoir que un popup javascript qui permet de s authentifier
        return (SecurityFilterChain)http.build();
    }
}
*/