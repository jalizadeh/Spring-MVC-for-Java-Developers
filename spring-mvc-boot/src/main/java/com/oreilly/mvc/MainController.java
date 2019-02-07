package com.oreilly.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/main")
public class MainController {
	
	@ResponseBody
	@RequestMapping("/")
	public String greeting() {
		return "Hello World by SpringBoot";
	}
}
