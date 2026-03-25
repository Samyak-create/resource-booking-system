package com.example.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Resource;

import jakarta.persistence.LockModeType;

@Repository
public interface ResourceRepo extends JpaRepository<Resource,Integer> {
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT r FROM Resource r WHERE r.id = :id")
	Optional<Resource> findByIdForUpdate(@Param("id") Integer id);
	  
	//find By name
    Optional<Resource> findByName(String name);
	
//	//find by booking 
//	List<Resource> findByBookedTrue();
//
//	//find by booking
//	List<Resource> findByBookedFalse();
//	


	List<Resource> findAll();
	
  
	
	
}
