package com.example.baitapquanlyuser.config;

import com.example.baitapquanlyuser.services.TblUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true) // enable @PreAuthorize and @PostAuthorize
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("tblUserService")
    TblUserService tblUserService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
//        auth.inMemoryAuthentication().withUser("admin").password("{noop}123456").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
             http
                .authorizeRequests()
                     .antMatchers(
                             "/js/**",
                             "/css/**",
                             "/images/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login").permitAll() // url page html
                    .loginProcessingUrl("/login") // equivalent call login controller
                    .defaultSuccessUrl("/listUser")
                    .failureUrl("/login?error")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .and()
                 .logout()
                     .logoutUrl("/logout")
                     .logoutSuccessUrl("/login")
                     .permitAll().and().exceptionHandling().accessDeniedPage("/403");
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider impl = new DaoAuthenticationProvider();
        impl.setUserDetailsService(tblUserService);
        impl.setPasswordEncoder(passwordEncoder());
        return impl ;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}
