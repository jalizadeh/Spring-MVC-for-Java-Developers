package com.oreilly.mvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.oreilly.mvc.data.services.ProjectService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT)
public class FullStackTests {

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void test() {
		String endpoint = "/project/api/1";
		String value = this.restTemplate.getForObject(endpoint, String.class);
		assert(value.contains("\"name\":\"Java Project\""));
	}
}