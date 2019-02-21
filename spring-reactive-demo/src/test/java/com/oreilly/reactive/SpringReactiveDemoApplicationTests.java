package com.oreilly.reactive;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringReactiveDemoApplicationTests {

	private Character[] letters = {'H','E','L','L','O',' ','W','O','R','L','D'};
	
	@Test
	public void test1() {
		System.out.println("Running Test #1");
		Flux.fromArray(letters)
			.subscribe(c -> System.out.println(c));
	}

	
	@Test
	public void test2() {
		System.out.println("Running Test #2");
		Flux.fromArray(letters)
			.buffer(3)
			.subscribe(c -> System.out.println(c));
	}
	
	@Test
	public void test3() throws InterruptedException{
		System.out.println("Running Test #3");
		Flux.fromArray(letters)
			.parallel(10)
			.runOn(Schedulers.elastic())
			.doOnEach(v -> {
				System.out.println(Thread.currentThread().getName());
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			})
			.subscribe(c -> System.out.println(c));
		
		System.out.println("Finished #3");
		Thread.sleep(10000);
		System.out.println("Finished #3 - After 10 sec");
	}
}
