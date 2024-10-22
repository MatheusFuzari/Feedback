package com.bosch.feedforward.config.security;


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
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
         http.csrf(csrf -> csrf.disable())
                 .sessionManagement(
                         session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                 )
                 .authorizeHttpRequests(
                         authorize -> authorize
                                 .requestMatchers("/api/auth/users/**").permitAll()
                                 .requestMatchers("/api/auth/roles/**").hasRole("ADMIN")
                                 .requestMatchers(HttpMethod.GET, "/api/auth/teste/user").hasRole("APPRENTICE")
                                 .requestMatchers(HttpMethod.GET, "/api/auth/teste/admin").hasRole("ADMIN")

                 ).addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

         return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

}
