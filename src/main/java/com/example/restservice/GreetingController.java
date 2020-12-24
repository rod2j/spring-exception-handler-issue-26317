package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {

		if ("err3".equals(name)) {
			throw new RuntimeException(
					new Exception(
							new IllegalArgumentException("ExceptionHandler will be resolved but cannot be invoked")));
		}
		if ("err2".equals(name)) {
			throw new RuntimeException(
					new IllegalArgumentException("ExceptionHandler will be resolved and can be invoked"));
		}
		if ("err1".equals(name)) {
			throw new IllegalArgumentException("ExceptionHandler will be resolved and can be invoked");
		}
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}
