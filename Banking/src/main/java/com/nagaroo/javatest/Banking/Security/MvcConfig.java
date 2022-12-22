package com.nagaroo.javatest.Banking.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
	
	@Override
	   public void addViewControllers(ViewControllerRegistry registry) {
	      
	      registry.addViewController("/").setViewName("login");
	     registry.addViewController("/login").setViewName("login");
	      registry.addViewController("/statement/userInput").setViewName("userinput");
	   }
}
