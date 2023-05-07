package com.example.demo.securityConfigurer;


import com.example.demo.frontend.LoginView;

import com.example.demo.repository.UserRepository;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import lombok.AllArgsConstructor;
import org.hibernate.dialect.MariaDBDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.util.Arrays;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfigure extends VaadinWebSecurity {


    private final DataSource dataSource;

    @Autowired
    private ApplicationContext applicationContext;

//    @Autowired
//    private final UserDetailsManager userDetailsManager;


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
        UserDetails user = User
                .withUsername("user")
                .password(passwordEncoder().encode("user12"))
                .roles("User")
                .build();
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.createUser(user);

       // ((ConfigurableApplicationContext) applicationContext).registerShutdownHook();

        return users;
    }

}


//    @Bean
//    UserDetailsManager userDetailsManager() {
//        UserDetails konsekrowany = User
//                .withUsername("user")
//                .password(passwordEncoder().encode("kgb2023"))
//                .roles("User")
//                .build();
//
//        UserDetails pastore = User
//                .withUsername("pastore")
//                .password(passwordEncoder().encode("pastore23"))
//                .roles("PASTORE")
//                .build();
//
//
//        return new InMemoryUserDetailsManager(Arrays.asList(konsekrowany, pastore));
//    }









