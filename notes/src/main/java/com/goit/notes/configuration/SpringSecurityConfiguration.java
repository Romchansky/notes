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
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {


    private final UserDetailsServiceImpl customUserDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());

        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("super_secret_password")
                .roles("ADMIN");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        //Страница /userInfo требует входа в систему как ROLE_USER
       // http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_USER')");

        // Только для роли Admin
       // http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");

        // Если нет прав (роли) для доступа, будет перенаправлен на страницу регистрации


        // config
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/user/register").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/listNotes") // поменять на правильный урл
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                // logout config
                .and().logout().logoutUrl("/logout");


    }


}
