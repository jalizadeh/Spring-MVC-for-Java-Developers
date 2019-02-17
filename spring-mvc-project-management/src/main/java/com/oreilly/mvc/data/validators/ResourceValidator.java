package com.oreilly.mvc.data.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.oreilly.mvc.data.entities.Resource;

public class ResourceValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Resource.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Resource resource = (Resource) target;
		if(resource.getName().length() < 5)
			errors.rejectValue("name", "resource.name", 
					"The resource name is too short. It must be longer than 5 characters.");
	}

	
}
