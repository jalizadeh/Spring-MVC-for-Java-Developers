package com.oreilly.mvc.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.oreilly.mvc.data.entities.Resource;
import com.oreilly.mvc.data.services.ResourceService;

@Component
public class ResourceConverter implements Converter<String, Resource> {

	@Autowired
	private ResourceService service;
	
	@Override
	public Resource convert(String source) {
		return service.find(new Long(source));
	}

}
