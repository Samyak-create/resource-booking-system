package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String fname;
	private String lname;
	
	@Column(unique=true)
	private String email;     
	
	private String password;
	private String role;

	public Employee() {}

	public Employee(Integer id, String fname, String lname, String email, String password, String role) {
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	public String getFname() { return fname; }
	public void setFname(String fname) { this.fname = fname; }
	public String getLname() { return lname; }
	public void setLname(String lname) { this.lname = lname; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public String getRole() { return role; }
	public void setRole(String role) { this.role = role; }
}