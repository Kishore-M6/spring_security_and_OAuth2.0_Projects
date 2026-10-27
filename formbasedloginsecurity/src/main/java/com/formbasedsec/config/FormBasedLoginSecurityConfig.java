    package com.formbasedsec.config;

    import jakarta.servlet.DispatcherType;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.authentication.AuthenticationProvider;
    import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
    import org.springframework.security.config.Customizer;
    import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.core.userdetails.User;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsPasswordService;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.provisioning.InMemoryUserDetailsManager;
    import org.springframework.security.web.SecurityFilterChain;

    @Configuration
    @EnableWebSecurity
    public class FormBasedLoginSecurityConfig {
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
        public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
            // DaoAuthenticationProvider automatically uses the UserDetailsService and PasswordEncoder beans
            DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userDetailsService);
            authenticationProvider.setUserDetailsPasswordService((UserDetailsPasswordService) userDetailsService);
            authenticationProvider.setPasswordEncoder(passwordEncoder);

            return authenticationProvider;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
            // Build each user explicitly and encode their password using the injected PasswordEncoder
            UserDetails kumar = User.builder()
                    .username("kumar")
                    .password(passwordEncoder.encode("kumar@1"))
                    .roles("customer") // Maps to "ROLE_customer"
                    .build();

            UserDetails raja = User.builder()
                    .username("raja")
                    .password(passwordEncoder.encode("raja@1"))
                    .roles("manager")
                    .build();

            UserDetails arunkarna = User.builder()
                    .username("arunkarna")
                    .password(passwordEncoder.encode("arunkarna@1"))
                    .roles("analyst")
                    .build();

            // Return the standard Spring Security in-memory manager implementation
            return new InMemoryUserDetailsManager(kumar, raja, arunkarna);
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
            return authenticationConfiguration.getAuthenticationManager();
        }

    }
