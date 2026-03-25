package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BookingRequest;
import com.example.demo.entities.Booking;
import com.example.demo.service.BookingService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/bookings")
public class BookingController {
	@Autowired 
	private BookingService bookingservice;
	
	@PostMapping
	public Booking addBooking(@RequestBody BookingRequest bookingrequest) {
		return bookingservice.addBooking(bookingrequest.getEmpId(),bookingrequest.getResId(),bookingrequest.getQuantity());
	}
	

}
