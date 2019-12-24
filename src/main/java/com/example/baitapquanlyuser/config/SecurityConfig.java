package com.example.baitapquanlyuser.config;

import com.example.baitapquanlyuser.services.TblUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
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
//        http.authorizeRequests().antMatchers("/listUser/**")
//                .hasAnyRole("ADMIN", "USER")
////                .access("hasRole('ROLE_ADMIN')")
//                .anyRequest().authenticated()
//                .and().formLogin()
//                .loginPage("/login").loginProcessingUrl("/j_spring_security_check").failureUrl("/login?error")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .and().logout().logoutSuccessUrl("/login?logout")
//                .and().csrf();
////                .and().exceptionHandling().accessDeniedPage("/403");
             http
                .authorizeRequests()
                     .antMatchers(
                             "/js/**",
                             "/css/**",
                             "/images/**").permitAll()
                    .antMatchers("/listUser/**")
                    .hasAnyRole("ADMIN", "USER")
                     .antMatchers("/listGroupRest/**")
                     .hasAnyRole("ADMIN", "USER")
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login").permitAll()
                    .defaultSuccessUrl("/listUser")
                    .failureUrl("/login?error")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .and()
                 .logout()
                     .permitAll().and().exceptionHandling().accessDeniedPage("/403");
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider impl = new DaoAuthenticationProvider();
        impl.setUserDetailsService(tblUserService);
        impl.setPasswordEncoder(passwordEncoder());
        impl.setHideUserNotFoundExceptions(false) ;
        return impl ;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}
