package com.hjbello.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com")
public class MvcConfig extends WebMvcConfigurerAdapter{
 	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	  if (!registry.hasMappingForPattern("/css/**")) {
	     registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
	  }
	  if (!registry.hasMappingForPattern("/js/**")) {
		     registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
		  }
	}

//	@Bean
//	public InternalResourceViewResolver viewResolver() {
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//		resolver.setPrefix("/WEB-INF/jsp/");
//		resolver.setSuffix(".jsp");
//	     resolver.setViewClass(JstlView.class);
//
//		return resolver;
//	}   
	
//	 @Bean
//	 UrlBasedViewResolver resolver(){
//	     UrlBasedViewResolver resolver = new UrlBasedViewResolver();
//
//	     resolver.setPrefix("/views/");
//	     resolver.setSuffix(".jsp");
//	     resolver.setViewClass(JstlView.class);
//
//	     return resolver;
//	 }

}