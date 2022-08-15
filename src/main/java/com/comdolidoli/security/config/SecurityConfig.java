package com.comdolidoli.security.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.comdolidoli.security.handler.CustomLoginSuccessHandler;

import lombok.RequiredArgsConstructor;

@Configurable
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final UserDetailsService userDetailsService;

    @Override
    public void configure(WebSecurity web){
        web.ignoring()
            .antMatchers("/resources/**")
            .antMatchers("/h2-console/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().authorizeRequests()
                .antMatchers("/login","/signup").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/myinfo").authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(customLoginSuccessHandler())
                .failureForwardUrl("/fail")
                .and()
                .logout()
                .logoutUrl("/logout");
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomLoginSuccessHandler customLoginSuccessHandler(){
        return new CustomLoginSuccessHandler();
    }
    @Bean
    public CustomAuthenticationProvider CustomAuthenticationProvider(){
        return new CustomAuthenticationProvider(userDetailsService,bCryptPasswordEncoder());
    }
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder){
        authenticationManagerBuilder.authenticationProvider(CustomAuthenticationProvider());
    }
}
