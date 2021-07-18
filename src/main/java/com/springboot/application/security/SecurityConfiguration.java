package com.springboot.application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	/**
	 * Here we are using In-Memory Authentication. Use of NoOpPasswordEncoder is deprecated so using BCryptPasswordEncoder()
	 * This is authentication
	 * @param auth
	 * @throws Exception
	 */
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser("mukul")
//				.password("admin").roles("USER", "ADMIN");
		String password = passwordEncoder().encode("admin");
		auth.inMemoryAuthentication().withUser("mukul").password(password).roles("USER","ADMIN");
	}

	/**
	 * Below menthod is used to override the existing configure menthod which shows
	 * on screen whenever we login the app. We are trying to say that for "/login"
	 * permitAll and for "/" and "todo" page access is required
	 * 
	 * This is authorization.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/login","/h2-console/**").permitAll()
		.antMatchers("/", "/*todo*/**").access("hasRole('ADMIN')").and().formLogin();
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

}
