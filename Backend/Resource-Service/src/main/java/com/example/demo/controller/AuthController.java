package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginDTO;
import com.example.demo.entities.Employee;
import com.example.demo.service.EmployeeService;

@RestController

@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private EmployeeService empservice;
	
	@PostMapping("/register")
	public String register(@RequestBody Employee employee) {
		System.out.println("Registering user: " + employee.getEmail());
		return empservice.register(employee);
	}
	

	
	@PostMapping("/login")
	public String login(@RequestBody LoginDTO loginDto) {
		System.out.println("Login attempt for: " + loginDto.getEmail());
		return empservice.login(loginDto.getEmail(),loginDto.getPassword());
	}
      	
}
