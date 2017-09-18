package com.hlaing.ng4boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="ng4boot", description="Spring Boot Rest api")
public class WebRestController {
	
	@ApiOperation(value="Get the hello api")
	@RequestMapping(value="/api/hello", method=RequestMethod.GET)
	public String hello() {
		return "Hello Angular4 SpringBoot App";
	}

}
