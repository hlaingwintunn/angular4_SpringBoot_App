package com.hlaing.ng4boot.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import com.hlaing.ng4boot.model.Booking;


@Transactional
public interface BookingRepository extends MongoRepository<Booking, String> {
	
	public Booking findByDeparture(String departure);

}
