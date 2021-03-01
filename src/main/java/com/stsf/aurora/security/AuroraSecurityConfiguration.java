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
import java.util.Scanner;

@Configuration
@EnableWebSecurity
public class AuroraSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private String PASSSWORD;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuroraSecurityConfiguration(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/",  "index", "/css/*", "/js/*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {

        this.PASSSWORD = generatePassword();

        Scanner sc = new Scanner(System.in);
        System.out.println("\n\n\n\n!!!!!!\nGENERATING NEW CREDENTIALS");
        System.out.printf("USERNAME - [%s] :: PASSWORD - [%s]\n", "storm", this.PASSSWORD);
        System.out.println("!!!!!!\n");
        System.out.println("[NOTE] The Credentials will expire upon restarting Aurora\n\n\n");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        UserDetails auroraUser = User.builder()
                .username("storm")
                .password(passwordEncoder.encode("password"))
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

