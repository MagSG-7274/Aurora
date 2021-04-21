package com.stsf.aurora.security;


import com.stsf.aurora.AuroraApplication;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

@Configuration
@EnableWebSecurity
public class AuroraSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private String PASSWORD;
    private String USERNAME;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuroraSecurityConfiguration(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
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

         this.getUserCredentials();


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
        int targetPasswordLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetPasswordLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private void getUserCredentials() {

        File f = new File(AuroraApplication.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        // Maybe there is a better way of doing this
        String location = f.toString().split("build")[0];
        location += "config.json";

        try {

            String content = new String(Files.readAllBytes(Paths.get(location)));
            JSONObject configJson = new JSONObject(content);

            String password = configJson.getString("password");
            String username = configJson.getString("username");

            this.PASSWORD = password;
            this.USERNAME = username;

        } catch (IOException | JSONException e) {

            System.out.println("Couldn't find json or not all required fields are present, falling back to defaults");
            this.PASSWORD = generatePassword();
            this.USERNAME = "storm";

            System.out.println("\n\n\n!!!!!!!!\nGENERATING NEW CREDENTIALS");
            System.out.printf("USERNAME - [%s] :: PASSWORD - [%s]\n", this.USERNAME, this.PASSWORD);
            System.out.println("!!!!!!!!");
            System.out.println("[NOTE] The credentials will expire upon restarting Aurora\n\n\n\n");

        }

    }
}

