package com.nnk.springboot.config;

import com.nnk.springboot.services.CustomLogoutHandler;
import com.nnk.springboot.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final CustomLogoutHandler logoutHandler;
    public SecurityConfig(CustomLogoutHandler logoutHandler) {
        this.logoutHandler = logoutHandler;
    }

    /**
     *
     * To load the user details like the username, the password, the role to authenticate the user
     *
     */
    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    /**
     *
     * To encode or check the password
     *
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     *
     * To configure an authentication provider using UserDetailsService and PasswordEncoder
     *
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     *
     * To manage the security
     *
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> {
                    csrf.disable();
                })
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/error/**","/403","/home","/login").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/user/**").hasAuthority("ADMIN");
                    auth.requestMatchers("/","/trade/**","/user/**","/ruleName/**","/rating/**","/bidList/**","/curvePoint/**").authenticated();
                    auth.anyRequest().permitAll();
                })
                .formLogin(formLogin-> formLogin
                        .loginPage("/login")
                        //.defaultSuccessUrl("/user/list")
                        .permitAll())
                .logout(httpSecurityLogoutConfigurer ->
                        httpSecurityLogoutConfigurer.logoutUrl("/logout"))

                .build();
    }
}
