package com.hlaing.ng4boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebRestController {
	
	@RequestMapping("/api/hello")
	public String hello() {
		return "Hello Angular4 SpringBoot App";
	}

}
