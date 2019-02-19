package com.oreilly.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;

import com.oreilly.mvc.converters.JulianDateConverter;
import com.oreilly.mvc.converters.ResourceConverter;
import com.oreilly.mvc.interceptors.GlobalInterceptor;

@Configuration
public class WebConfiguration implements WebMvcConfigurer{
	
	@Autowired
	private ResourceConverter resourceConverter;
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
		registry.enableContentNegotiation(new MappingJackson2XmlView(), 
				new MappingJackson2JsonView());
	}

	/*
	 * this method can be used for changing the default response type.
	@Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON_UTF8);
        configurer.defaultContentType(MediaType.APPLICATION_XML);
    }
	*/
	
	@Bean
	public ViewResolver resourceBundleViewResolver() {
		ResourceBundleViewResolver viewResolver = new ResourceBundleViewResolver();
		viewResolver.setBasename("views");
		viewResolver.setOrder(0);
		return viewResolver;
	}

	/*
	 * `path` will restrict the interceptor scope. 
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new GlobalInterceptor()).addPathPatterns("/project/**");
		WebMvcConfigurer.super.addInterceptors(registry);
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		WebMvcConfigurer.super.addFormatters(registry);
		registry.addConverter(resourceConverter);
		registry.addConverter(new JulianDateConverter());
	}

	/*
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//NO other domain is allowed
		//registry.addMapping("/**").allowedMethods("");
		
		//any request ONLY from this domain is allowed
		//registry.addMapping("/**").allowedOrigins("http://somedomain.com").allowedMethods("*");
	}
	*/
}
