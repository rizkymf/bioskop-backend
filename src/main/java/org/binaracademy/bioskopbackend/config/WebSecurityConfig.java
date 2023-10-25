package org.binaracademy.bioskopbackend.config;

import org.binaracademy.bioskopbackend.enumeration.ERole;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/api/movies/*").permitAll()
                .antMatchers("/api/invoice").hasAuthority(ERole.ROLE_ADMIN.name()).and()
                .formLogin()
                .loginPage("/login").permitAll().and()
                .logout().permitAll().and()
                .httpBasic();
    }
}
