package com.example.demo.securityConfigurer;


import com.example.demo.frontend.LoginView;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfigure extends VaadinWebSecurity {


    private final DataSource dataSource;

    @Autowired
    private ApplicationContext applicationContext;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        setLoginView(http, LoginView.class);


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsManager users(DataSource dataSource) {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);

        // Check if user already exists
        if (!users.userExists("user")) {
            UserDetails user = User
                    .withUsername("user")
                    .password(passwordEncoder().encode("user.password"))
                    .roles("User")
                    .build();

            users.createUser(user);
        }

        return users;

    }
}












