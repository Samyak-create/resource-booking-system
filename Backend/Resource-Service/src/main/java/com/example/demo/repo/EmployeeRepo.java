package com.example.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {

	
	Employee save(Employee emp);
	
	List<Employee> findAll();

	Optional<Employee> findByEmail(String email);
}
