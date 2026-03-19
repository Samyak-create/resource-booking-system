package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Employee;
import com.example.demo.repo.EmployeeRepo;
@Service
public class EmployeeServiceImpl implements EmployeeService {
  
	@Autowired
	private EmployeeRepo emprepo;
	@Override
	public Employee addEmployee(Employee emp) {
		// TODO Auto-generated method stub
		emprepo.save(emp);
		return emp;
		
	}
	public List<Employee> allEmployees(){
	  return emprepo.findAll();
	}

}
