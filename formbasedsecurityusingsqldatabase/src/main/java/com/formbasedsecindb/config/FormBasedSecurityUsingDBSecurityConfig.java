package com.formbasedsecindb.config;


import jakarta.servlet.DispatcherType;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class FormBasedSecurityUsingDBSecurityConfig {
    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(login -> login.loginPage("/login.htm")
                        .loginProcessingUrl("/j_login").usernameParameter("j_username")
                        .passwordParameter("j_password").defaultSuccessUrl("/home.htm"))
                .logout(httpSecurityLogoutConfigurer ->
                        httpSecurityLogoutConfigurer.logoutUrl("/j_logout").logoutSuccessUrl("/logout.htm"));
        http.csrf(csrf -> csrf.disable());


        http.authorizeHttpRequests(auth ->
                // 1. CRITICAL: Allow Spring MVC to forward to your JSP files
                auth.dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
                        .requestMatchers("/home.htm").permitAll()
                        .requestMatchers("/loan-info.htm").fullyAuthenticated()
                        .requestMatchers("/login.htm").permitAll()
                        .requestMatchers("/logout.htm").permitAll())
        ;
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder) {
        // DaoAuthenticationProvider automatically uses the UserDetailsService and PasswordEncoder beans
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    //        @Bean
//        public AuthenticationManager authenticationManager(AuthenticationProvider authentiationProvider) throws Exception {
//            ProviderManager providerManager=new ProviderManager(authentiationProvider);
//            return providerManager;
//        }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
