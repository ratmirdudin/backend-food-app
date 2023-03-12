package com.ratmirdudin.backendfoodapp.configs;

import com.ratmirdudin.backendfoodapp.security.CustomUserDetailsService;
import com.ratmirdudin.backendfoodapp.user.enums.RoleEnum;
import com.ratmirdudin.backendfoodapp.security.jwt.JwtProvider;
import com.ratmirdudin.backendfoodapp.security.jwt.JwtUsernamePasswordAuthenticationFilter;
import com.ratmirdudin.backendfoodapp.security.jwt.JwtVerifierAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtProvider jwtProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwtUsernamePasswordAuthenticationFilter jwtUsernamePasswordAuthenticationFilter
                = new JwtUsernamePasswordAuthenticationFilter(authenticationManager(), jwtProvider);
        jwtUsernamePasswordAuthenticationFilter.setFilterProcessesUrl("/api/auth/login");
        JwtVerifierAuthorizationFilter jwtVerifierAuthorizationFilter = new JwtVerifierAuthorizationFilter(jwtProvider);
        http
//                .cors().and()
                .csrf().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(jwtUsernamePasswordAuthenticationFilter)
                .addFilterAfter(jwtVerifierAuthorizationFilter, JwtUsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/users").permitAll()
                .antMatchers(HttpMethod.GET, "/api/foods/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/users/**").hasAnyAuthority(RoleEnum.ROLE_ADMIN.toString())
                .and()
                .authorizeHttpRequests().anyRequest().authenticated();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}