package com.edutecno.SitioProtegidoConCredenciales.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccesHandler;

	@Autowired
	public WebSecurityConfig(AuthenticationSuccessHandler authenticationSuccesHandler) {
		this.authenticationSuccesHandler = authenticationSuccesHandler;
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/admin/**").hasAuthority("ADMIN")
				.antMatchers("/client/**").hasAuthority("CLIENT").antMatchers("/login").permitAll().anyRequest()
				.authenticated().and().formLogin().loginPage("/login").successHandler(authenticationSuccesHandler)
				.failureUrl("/login?error=true").usernameParameter("email").passwordParameter("password").and()
				.exceptionHandling().accessDeniedPage("/errors/403");
		;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}
}
