package com.ricoh.igae;

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

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com")
public class MvcConfig extends WebMvcConfigurerAdapter{
  
 @Override
 public void addResourceHandlers(ResourceHandlerRegistry registry) {
   if (!registry.hasMappingForPattern("/css/**")) {
      registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
   }
 }

 @Bean
 public InternalResourceViewResolver viewResolver() {
  InternalResourceViewResolver resolver = new InternalResourceViewResolver();
  resolver.setPrefix("/WEB-INF/jsp/");
  resolver.setSuffix(".jsp");
  return resolver;
 }   

}