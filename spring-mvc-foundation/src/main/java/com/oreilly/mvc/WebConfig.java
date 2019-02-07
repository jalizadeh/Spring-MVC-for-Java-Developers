package com.oreilly.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc 
//look into this package for annotated files 
// & convert them to Beans in IOC Container
@ComponentScan("com.oreilly.mvc.controllers")
public class WebConfig {
	
	//for accessing views, it must be hold as a bean
	@Bean
	public InternalResourceViewResolver viewResolver() {
		System.out.println("WC: viewResolver");
		//it will return the requested page -> /WEB-INF/views/page.jsp
		InternalResourceViewResolver viewResolverBean = new InternalResourceViewResolver();
		viewResolverBean.setPrefix("/WEB-INF/views/");
		viewResolverBean.setSuffix(".jsp");
		return viewResolverBean;
	}
}
