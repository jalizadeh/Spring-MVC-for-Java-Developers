package com.oreilly.mvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class FullStackTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void postTest() {
		String endpoint = "/project/add";
		MultiValueMap<String, String> dataMap = new LinkedMultiValueMap<>();
		dataMap.add("name", "Simple Project");
		dataMap.add("description", "Simple Description");

		HttpEntity<MultiValueMap<String, String>> requestData = new HttpEntity<MultiValueMap<String, String>>(dataMap,
				new HttpHeaders());

		String value = this.restTemplate.postForLocation(endpoint, requestData, String.class).getPath();
		assert (value.equals("/app/home/"));
	}

	@Test
	public void test() {
		String endpoint = "/project/api/1";
		String value = this.restTemplate.getForObject(endpoint, String.class);
		assert (value.contains("\"name\":\"Java Project\""));
	}

}
