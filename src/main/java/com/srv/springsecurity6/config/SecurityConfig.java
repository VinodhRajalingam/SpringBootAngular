package com.srv.springsecurity6.config;

import com.srv.springsecurity6.entity.MyUser;
import com.srv.springsecurity6.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig implements UserDetailsService {

    @Autowired MyUserRepository myUserRepository;
    @Bean
    public SecurityFilterChain applicationSecurity(HttpSecurity httpSecurity) throws Exception {

        DefaultSecurityFilterChain http = httpSecurity
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/","/*.html","/newUserRegister").permitAll())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/newUserRegister").hasRole("ADMIN"))
                //.authorizeHttpRequests(auth -> auth.requestMatchers("/googleOAuth")).oauth2Login()
                //.and()
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/index.html")
                .failureUrl("/loginFailure").permitAll()
                .and()
                .logout()
                //.logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and()
                .build();
        return http;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = myUserRepository.loadUserByUsername(username);
        if(myUser != null) {
            return myUser;
        }else {
            throw new UsernameNotFoundException("Username not found : "+username);
        }
    }
}
