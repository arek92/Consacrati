package com.example.demo.securityConfigurer;


import com.example.demo.frontend.LoginView;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
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
    public UserDetailsManager users(DataSource dataSource, Environment env) {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);

        // Check if user already exists
        if (!users.userExists("user")) {
            String password = env.getProperty("spring.user.password");
            UserDetails user = User
                    .withUsername("user")
                    .password(passwordEncoder().encode(password))
                    .roles("User")
                    .build();

            users.createUser(user);
        }
        if (!users.userExists("pastore")) {
            String password = env.getProperty("spring.pastore.password");
            UserDetails pastore = User
                    .withUsername("pastore")
                    .password(passwordEncoder().encode(password))
                    .roles("Pastore")
                    .build();

            users.createUser(pastore);
        }

        return users;

    }
}












