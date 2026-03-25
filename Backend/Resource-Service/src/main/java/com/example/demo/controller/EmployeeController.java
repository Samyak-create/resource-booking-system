package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/employees")
public class EmployeeController {
	
@Autowired
private EmployeeService empservice;

	@PostMapping
	public Employee addEmployee(@RequestBody Employee emp) {
		return  empservice.addEmployee(emp);
		
	}
	
	@GetMapping
	public List<Employee> getAllEmployee(){
		return empservice.allEmployees();
	}
	
}
