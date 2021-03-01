package com.stsf.aurora.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Random;

@Configuration
@EnableWebSecurity
public class AuroraSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private String PASSWORD;
    private String USERNAME = "storm";
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuroraSecurityConfiguration(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/*").permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {

        this.PASSWORD = generatePassword();

        System.out.println("\n\n\n!!!!!!!!\nGENERATING NEW CREDENTIALS");
        System.out.printf("USERNAME - [%s] :: PASSWORD - [%s]\n", this.USERNAME, this.PASSWORD);
        System.out.println("!!!!!!!!");
        System.out.println("[NOTE] The credentials will expire upon restarting Aurora\n\n\n\n");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignored){}

        UserDetails auroraUser = User.builder()
                .username(this.USERNAME)
                .password(passwordEncoder.encode(this.PASSWORD))
                .roles("AURORA")
                .build();

        return new InMemoryUserDetailsManager(
                auroraUser
        );
    }

    private String generatePassword() {

        //From: https://www.baeldung.com/java-random-string

        int leftLimit = 97; // a
        int rightLimit = 122; // z
        int targetPasswordLenght = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetPasswordLenght)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}

