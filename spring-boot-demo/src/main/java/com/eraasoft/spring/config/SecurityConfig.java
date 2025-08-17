package com.eraasoft.spring.config;


import com.eraasoft.spring.helper.CustomAccessDeniedHandler;
import com.eraasoft.spring.helper.CustomAuthenticationEntryPoint;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Stream;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private CustomAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public UserDetailsService userDetailsService() {
        List<UserDetails> users = Stream.of(
                User.withUsername("hamdy").password("{noop}hamdy123").roles("USER").build(),
                User.withUsername("ali").password("{noop}ali123").roles("USER", "ADMIN").build(),
                User.withUsername("ahmed").password("{noop}ahmed123").roles("USER", "ADMIN", "MANAGER").build()
        ).toList();

        return new InMemoryUserDetailsManager(users);
    }

//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource) {
//       JdbcUserDetailsManager  userDetailsManager = new JdbcUserDetailsManager(dataSource);
//        return userDetailsManager;
//
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/students").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/save-student").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedHandler(accessDeniedHandler)
                        .authenticationEntryPoint(authenticationEntryPoint)
                )
               .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
