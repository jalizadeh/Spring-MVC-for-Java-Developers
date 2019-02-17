package com.oreilly.mvc.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.oreilly.mvc.data.validators.ProjectValidator;

/*
 * All controllers have access to methods inside this advisor
 */

@ControllerAdvice(annotations=Controller.class)
public class GlobalControllerAdvice {
	
	@ModelAttribute("currentDate")
	public Date getCurrentDate() {
		return new Date();
	}
	
	
	//from ProjectController
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new ProjectValidator());
	}
	
	//from ResourceController
	@ExceptionHandler(value=NullPointerException.class)
	public String handleError(HttpServletRequest request) {
		return "controller_error";
	}
}
