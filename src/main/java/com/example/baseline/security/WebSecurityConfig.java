package com.example.baseline.security;

import com.example.baseline.util.constants.Privillages;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
    private static final String[] WHITELIST ={
            "/",
            "/login",
            "/register",
            "/db-console/**",
            "/css/**",
            "/fonts/**",
            "/images/**",
            "/js/**",
            "/post/**",
            "/update_photo",
            "/forgot-password",
            "/reset-password",
            "/change-password",
    };
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.authorizeHttpRequests(auth ->
                auth.requestMatchers(WHITELIST)
                .permitAll()
                .requestMatchers("/profile/**").authenticated()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/editor/**").hasAnyRole("EDITOR","ADMIN")
                .requestMatchers("/test/**").hasAuthority(Privillages.ACCESS_ADMIN_PANEL.getPrivillage()))
                .formLogin(formLogin ->
                 formLogin.loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true)
                .failureForwardUrl("/login")
                .permitAll())
                .rememberMe(rem->rem.rememberMeParameter("remember-me"))
                .logout(logout->
                 logout.logoutUrl("/logout")
                .logoutSuccessUrl("/"))
                .httpBasic();

        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();


               return httpSecurity.build();
    };

}
