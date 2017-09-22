package com.hlaing.ng4boot.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hlaing.ng4boot.dao.BookingRepository;
import com.hlaing.ng4boot.model.Booking;
import com.hlaing.ng4boot.util.SpringLoggingHelper;

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
	@PutMapping("/create")
	public Map<String, Object> create(Booking booking) {
		booking.setTravelDate(new Date());
		booking = bookingRepository.save(booking);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("message", "Booking created successfully");
		dataMap.put("status", "1");
		dataMap.put("booking", booking);
	    return dataMap;
	}
	
	/**
	 * GET /read  --> Read a booking by booking id from the database.
	 */
	@PostMapping("/read")
	public Map<String, Object> read(@RequestParam String bookingId) {
		Booking booking = bookingRepository.findOne(bookingId);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("message", "Booking found successfully");
		dataMap.put("status", "1");
		dataMap.put("booking", booking);
	    return dataMap;
	}
	
	/**
	 * GET /update  --> Update a booking record and save it in the database.
	 */
	@PostMapping("/update")
	public Map<String, Object> update(@RequestParam String bookingId, @RequestParam String psngrName) {
		Booking booking = bookingRepository.findOne(bookingId);
		booking.setPsngrName(psngrName);
		booking = bookingRepository.save(booking);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("message", "Booking updated successfully");
		dataMap.put("status", "1");
		dataMap.put("booking", booking);
	    return dataMap;
	}
	
	/**
	 * GET /delete  --> Delete a booking from the database.
	 */
	@DeleteMapping("/delete")
	public Map<String, Object> delete(@RequestParam String bookingId) {
		bookingRepository.delete(bookingId);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("message", "Booking deleted successfully");
		dataMap.put("status", "1");
	    return dataMap;
	}
	
	/**
	 * GET /read  --> Read all booking from the database.
	 */
	@GetMapping("/read-all")
	public Map<String, Object> readAll() {
		List<Booking> bookings = bookingRepository.findAll();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("message", "Booking found successfully");
		dataMap.put("totalBooking", bookings.size());
		dataMap.put("status", "1");
		dataMap.put("bookings", bookings);
		
		//Logback example line
		logger.debug("This is booking debug message");
		new SpringLoggingHelper().helpMethod();
		
	    return dataMap;
	}

}
