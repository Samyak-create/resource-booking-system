package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Resource;

@Repository
public interface ResourceRepo extends JpaRepository<Resource,Integer> {
	//find By name
     Resource findByName(String name);
	
	//find by booking 
	List<Resource> findByBookedTrue();

	//find by booking
	List<Resource> findByBookedFalse();
	
	Resource findById(int id);

	List<Resource> findAll();
	
}
