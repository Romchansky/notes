package com.goit.notes.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class TestSecurityConfiguration extends WebSecurityConfigurerAdapter {


    private final UserDetailsServiceImpl customUserDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());

        auth.inMemoryAuthentication().withUser("admin").password("12345678").roles("ADMIN");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/listAllUsers").access("hasRole('ROLE_ADMIN')");

        http.csrf ().disable ()
                .authorizeRequests ()
                .antMatchers ("/user/registration").permitAll ()
                .anyRequest ().authenticated ()
                .and ()
                .formLogin ()
                .loginPage ("/login")
                .defaultSuccessUrl ("/")
                .permitAll ()
                .and ()
                .logout ()
                .permitAll ();
    }


}
