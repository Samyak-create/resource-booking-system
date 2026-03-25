package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Booking;
import com.example.demo.entities.Resource;

import jakarta.persistence.LockModeType;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Integer>{
	
	
}
