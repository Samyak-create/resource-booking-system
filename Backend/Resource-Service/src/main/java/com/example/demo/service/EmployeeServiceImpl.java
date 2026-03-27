package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // ✅ import

import com.example.demo.entities.Employee;
import com.example.demo.repo.EmployeeRepo;
import com.example.demo.security.JwtUtil;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private EmployeeRepo emprepo;

    @Override
    public Employee addEmployee(Employee emp) {
        emprepo.save(emp);
        return emp;
    }

    public List<Employee> allEmployees() {
        return emprepo.findAll();
    }

    public String register(Employee employee) {

        //  encrypt password
        employee.setPassword(encoder.encode(employee.getPassword()));

        //  set default role
        employee.setRole("ROLE_USER");

        emprepo.save(employee); // ✅ fixed

        return "User Registered Successfully";
    }
    
    
    public String login(String email,String password) {
    	System.out.println("Login service attempt for: " + email);
    	Employee emp=emprepo.findByEmail(email)
    			.orElseThrow(()-> new RuntimeException("User Not Found"));
    	
    	
    	if(encoder.matches(password,emp.getPassword())) {
    		System.out.println("Password match for: " + email);
    		return jwtUtil.generateToken(emp.getEmail(), emp.getRole(), emp.getId());
    		
    	}else {
    		System.out.println("Invalid password for: " + email);
    		throw new RuntimeException("Invalid Password");
    	}
    }
}