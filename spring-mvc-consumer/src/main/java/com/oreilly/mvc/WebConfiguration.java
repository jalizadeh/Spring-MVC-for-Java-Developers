package com.oreilly.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.oreilly.mvc.converters.JulianDateConverter;
import com.oreilly.mvc.interceptors.GlobalInterceptor;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public Jaxb2Marshaller marshaller() {
		
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.oreilly.ws.generated");
		return marshaller;
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {

			registry.addMapping("/**").allowedOrigins("http://somedomain.com").allowedMethods("*");
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		super.addFormatters(registry);
		registry.addConverter(new JulianDateConverter());
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new GlobalInterceptor()).addPathPatterns("/project/**");
		super.addInterceptors(registry);
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}

}
