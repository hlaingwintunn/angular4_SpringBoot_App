package com.hlaing.ng4boot.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hlaing.ng4boot.dao.BookingRepository;
import com.hlaing.ng4boot.model.Booking;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value="ng4boot", description="Spring Boot Rest api")
public class WebRestController {
	private static final Logger logger = LoggerFactory.getLogger(WebRestController.class);
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@ApiOperation(value="Get the hello api")
	@RequestMapping(value="/hello", method=RequestMethod.GET)
	public String hello() {
		return "Hello Angular4 SpringBoot App";
	}
	
	/**
	 * 
	 * @param booking
	 * @return 
	 */
	@ApiOperation(value="Create the booking")
	@RequestMapping(value="/create", method=RequestMethod.PUT)
	public Map<String, Object> create(Booking booking) {
		booking.setTravelDate(new Date());
		booking = bookingRepository.save(booking);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("message", "Booking created successfully");
		dataMap.put("status", "1");
		dataMap.put("booking", booking);
	    return dataMap;
	}

}
