package com.jane.springboot.employees.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails john = User.builder()
                .username("john")
                .password("{noop}root")
                .roles("EMPLOYEE")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}root")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}root")
                .roles("EMPLOYEE", "MANAGER","ADMIN")
                .build();
        return new InMemoryUserDetailsManager(john, mary, susan);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
            configurer
                    .requestMatchers("/docs/**", "/swagger-ui/**","/v3/api-docs/**","swagger-ui.html/**").permitAll()
                    .requestMatchers(HttpMethod.GET,"/api/employes").hasRole("EMPLOYEE")
                    .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                    .requestMatchers(HttpMethod.POST, "api/employees").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.PUT,"/api/employees/**").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.DELETE, "api/employees/**").hasRole("ADMIN")
        );

        http.httpBasic(httpBasicCustomizer -> httpBasicCustomizer.disable());

//        Use http Basic Authenticaion
        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf.disable());

        http.exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint(authenticationEntryPoint()));

        return http.build();
    }

    public AuthenticationEntryPoint authenticationEntryPoint(){
        return (request, response, authException) ->{
          response.setStatus(HttpStatus.UNAUTHORIZED.value());

          response.setContentType("application/json");

          response.setHeader("WWW-AUTHENTICATE", "");

          response.getWriter().write("{\"error\":\"Unauthorised access\"}");
        };
    }
}
