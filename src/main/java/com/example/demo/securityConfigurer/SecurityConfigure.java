package com.example.demo.securityConfigurer;

//import com.example.demo.frontend.LoginView;
import com.example.demo.frontend.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfigure extends VaadinWebSecurity {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        setLoginView(http, LoginView.class);
    }

    @Bean
    public PasswordEncoder getBcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();

    }

    @Bean
    UserDetailsManager userDetailsManager() {
        UserDetails konsekrowany = User
                .withUsername("Koinonia")
                .password(getBcryptPasswordEncoder().encode("kgb2023"))
                .roles("User")
                .build();

        UserDetails pastore = User
                .withUsername("pastore")
                .password(getBcryptPasswordEncoder().encode("pastore23"))
                .roles("PASTORE")
                .build();


        return new InMemoryUserDetailsManager(Arrays.asList(konsekrowany, pastore));
    }



}

