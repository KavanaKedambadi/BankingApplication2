package com.nagaroo.javatest.Banking.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class webSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable().authorizeRequests()
//        .antMatchers("/statement/userInput").hasAnyRole("user","admin","test").anyRequest().authenticated()
       // .antMatchers("/statement/getAccountSummary").hasAnyRole("USER","ADMIN").anyRequest().authenticated()
		//.and()
		.and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/statement/userInput",true)
		.and().logout()  
        .permitAll().logoutSuccessUrl("/login")
		.and().sessionManagement().maximumSessions(2).expiredUrl("/");
		
		
		
		  	
		
		
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth
		.inMemoryAuthentication().withUser("user").password("{noop}user").roles("USER").and()
		.withUser("admin").password("{noop}admin").roles("ADMIN")
		.and().withUser("test").password("{noop}test").roles("TEST");
		
		
		
	}
	
	
	
	
	}