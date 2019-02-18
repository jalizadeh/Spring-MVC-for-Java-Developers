package com.oreilly.mvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.oreilly.mvc.data.services.ProjectService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FullStackTests {

	@Autowired
	private ProjectService projectService;
	
	@Test
	public void contextLoads() {
		assert(projectService != null);
	}
}