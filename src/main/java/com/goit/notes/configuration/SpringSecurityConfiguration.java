package com.goit.notes.configuration;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @SneakyThrows
    @Autowired
    public void configureGlobal(BCryptPasswordEncoder passwordEncoder, 
            UserDetailsService userDetailsService, AuthenticationManagerBuilder auth) {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("super_secret_password")
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Страница /userInfo требует входа в систему как ROLE_USER
        // http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_USER')");
        // Только для роли Admin
        // http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");
        // Если нет прав (роли) для доступа, будет перенаправлен на страницу регистрации
        // config
        http.csrf().disable()
                .authorizeRequests()
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
