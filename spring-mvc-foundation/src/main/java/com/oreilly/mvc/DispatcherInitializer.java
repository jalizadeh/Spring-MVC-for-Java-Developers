package com.oreilly.mvc;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	//Establish root application context, for global beans & services,
	// that may be shared across the application
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootConfig.class};
	}

	//indicate the config classes
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {WebConfig.class};
	}

	//indicate the mappings
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

}
