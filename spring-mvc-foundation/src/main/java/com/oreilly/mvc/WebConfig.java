package com.oreilly.mvc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc 
//look into this package for annotated files 
// & convert them to Beans in IOC Container
@ComponentScan("com.oreilly.mvc")
public class WebConfig {
	
}
