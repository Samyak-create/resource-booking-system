package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entities.Employee;


public interface EmployeeService {
   public Employee addEmployee(Employee emp);
   
   public List<Employee> allEmployees();
}
