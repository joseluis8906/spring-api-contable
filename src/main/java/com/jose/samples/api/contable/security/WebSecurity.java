/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jose.samples.api.contable.security;

/**
 *
 * @author jose
 */

import static com.jose.samples.api.contable.security.SecurityConstants.SIGN_UP_URL;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
    
    private UserDetailsService userDetailsService;
    
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public WebSecurity (UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        
        this.userDetailsService = userDetailsService;
        
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        
    }
    
    @Override
    protected void configure (HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
				.antMatchers(HttpMethod.GET, "/v1/puc/clases*").permitAll()
				.antMatchers(HttpMethod.GET, "/v1/puc/clases/*").permitAll()
				.antMatchers(HttpMethod.POST, "/v1/puc/clases").permitAll()
				.antMatchers(HttpMethod.PUT, "/v1/puc/clases/*").permitAll()
				.antMatchers(HttpMethod.DELETE, "/v1/puc/clases/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    
    @Override
    public void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
    
    @Bean
    CorsConfigurationSource corsConfigurationSource () {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
