package com.nnk.springboot.config;

import com.nnk.springboot.services.CustomLogoutHandler;
import com.nnk.springboot.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * To manage the security
 *
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final CustomLogoutHandler logoutHandler;
    public SecurityConfig(CustomLogoutHandler logoutHandler) {
        this.logoutHandler = logoutHandler;
    }


    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    /*@Bean
    public PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices(UserDetailsService userDetailsService){
        return new PersistentTokenBasedRememberMeServices("uniqueAndSecret",userDetailsService,new InMemoryTokenRepositoryImpl());
    }*/

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> {
                    csrf.disable();
                })
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/error/**","/403","/home").permitAll();
                    auth.requestMatchers("/**","/login","/","/user/**").permitAll();
                    auth.requestMatchers("/bidList/**","/curvePoint/**","/rating/**","/ruleName/**","/trade/**").authenticated();
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