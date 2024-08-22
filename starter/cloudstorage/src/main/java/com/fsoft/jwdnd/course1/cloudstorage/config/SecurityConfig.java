package com.fsoft.jwdnd.course1.cloudstorage.config;

import com.fsoft.jwdnd.course1.cloudstorage.services.UserService;
import com.fsoft.jwdnd.course1.cloudstorage.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        request -> request
                                .requestMatchers("/user/getSignup", "/user/postSignup", "/css/**", "/js/**")
                                .permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(
                        login -> login
                                .loginPage("/user/login")
                                .loginProcessingUrl("/processLogin")
                                .defaultSuccessUrl("/user/home")
                                .permitAll()
                )
                .logout(
                        logout -> logout
                                .logoutUrl("/user/logout")
                                .logoutSuccessUrl("/user/login?logout=true")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                                .permitAll()
                );
        return http.build();
    }
}
