package br.unipe.academia.application.cfg; 


import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.ComponentScan; 
import org.springframework.context.annotation.Configuration; 
import org.springframework.context.annotation.ImportResource; 
import org.springframework.web.servlet.config.annotation.EnableWebMvc; 
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry; 
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter; 
 import org.springframework.web.servlet.view.InternalResourceViewResolver; 
 import org.springframework.web.servlet.view.JstlView; 
 

 @Configuration 
 @EnableWebMvc 
 @ImportResource("classpath*:persistence.xml") 
 @ComponentScan(basePackages = {"br.unipe.academia"}) 
 public class AppConfig extends WebMvcConfigurerAdapter{ 
 

 	private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/jsp/"; 
 	private static final String VIEW_RESOLVER_SUFFIX = ".jsp"; 
 

 	@Bean(name = "jspViewResolver") 
 	public InternalResourceViewResolver internalResourceViewResolver() { 
 		InternalResourceViewResolver resolver = new InternalResourceViewResolver(); 
 		resolver.setPrefix(VIEW_RESOLVER_PREFIX); 
 		resolver.setSuffix(VIEW_RESOLVER_SUFFIX); 
 		resolver.setViewClass(JstlView.class); 
 		return resolver; 
 	} 
 	 
 	@Override 
 	public void addResourceHandlers(ResourceHandlerRegistry registry) { 
 	    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/"); 
 	} 
 

 } 
