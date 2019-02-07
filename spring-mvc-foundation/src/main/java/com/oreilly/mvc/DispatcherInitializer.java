package com.oreilly.mvc;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	//Establish root application context, for global beans & services,
	// that may be shared across the application
	@Override
	protected Class<?>[] getRootConfigClasses() {
		System.out.println("DI: getRootConfigClasses");
		return new Class[] {RootConfig.class};
	}

	//indicate the config classes
	@Override
	protected Class<?>[] getServletConfigClasses() {
		System.out.println("DI: getServletConfigClasses");
		return new Class[] {WebConfig.class};
	}

	//indicate the mappings
	@Override
	protected String[] getServletMappings() {
		System.out.println("DI; getServletMappings");
		return new String[] {"/"};
	}

}
