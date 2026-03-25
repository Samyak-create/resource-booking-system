package com.example.demo.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Booking;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Resource;
import com.example.demo.repo.BookingRepo;
import com.example.demo.repo.EmployeeRepo;
import com.example.demo.repo.ResourceRepo;

import jakarta.transaction.Transactional;

@Service
public class BookingServiceImpl implements BookingService {
@Autowired
private BookingRepo bookrepo;
@Autowired
private EmployeeRepo employeeRepo;

@Autowired
private ResourceRepo resourceRepo;

@Transactional
@Override
public Booking addBooking(Integer empid, Integer resid, Integer quantity) {
	// TODO Auto-generated method stub
	
	int qty=(quantity == null || quantity <=0)?1:quantity;
	
	Employee emp =employeeRepo.findById(empid).orElseThrow();
	Resource res=resourceRepo.findByIdForUpdate(resid).orElseThrow();
	
	if( res.getAvailableQuantity() < qty) {
	throw new RuntimeException ("Not available ");
	}
	Booking booking = new Booking();
	booking.setEmp(emp);
	booking.setRes(res);
	booking.setQuantity(qty);
	booking.setBookingDate(LocalDate.now());
	
	res.setAvailableQuantity(res.getAvailableQuantity()-qty);
	
	return bookrepo.save(booking);
	
	
	
	
}
	
	
}
