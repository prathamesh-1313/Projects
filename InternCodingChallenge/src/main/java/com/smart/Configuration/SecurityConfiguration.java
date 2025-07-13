package com.smart.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private CustomAuthSuccessHandler customAuthSuccessHandler;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.csrf(csrf-> csrf.disable())
		.authorizeHttpRequests(auth -> auth
		.requestMatchers("/user/**").hasRole("USER")
		.requestMatchers("/admin/**").hasRole("ADMIN")
		.requestMatchers("/store-owner/**").hasRole("OWNER")
		.requestMatchers("/**").permitAll()
		)
		.formLogin(form -> form
        		.loginPage("/signin")
        		.loginProcessingUrl("/checkcred")
        		.successHandler(customAuthSuccessHandler)
        );		
		
		
		
		
		
		
		return httpSecurity.build();
		
	}
	
	
	@Bean
	public UserDetailsService getUserDetailsService() {
		
		return new UserDetailServiceImpl();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	

	
	@Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(getUserDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authBuilder.authenticationProvider(daoAuthenticationProvider());
        return authBuilder.build();
    }
}
	
	

