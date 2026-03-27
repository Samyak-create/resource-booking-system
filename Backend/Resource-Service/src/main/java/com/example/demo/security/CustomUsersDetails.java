package com.example.demo.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entities.Employee;

public class CustomUsersDetails implements UserDetails {
  
	private Employee employee;
	
	public CustomUsersDetails(Employee employee) {
		this.employee=employee;
	}
	@Override
	public Collection<? extends GrantedAuthority>getAuthorities(){
		return List.of(new SimpleGrantedAuthority(employee.getRole()));
	}
	
	@Override 
	public String getPassword() {
		return employee.getPassword();
	}
	
	@Override
	public String getUsername() {
		return employee.getEmail();
	}
	@Override 
	public boolean isAccountNonExpired() {return true;}
	 @Override public boolean isAccountNonLocked() { return true; }
	    @Override public boolean isCredentialsNonExpired() { return true; }
	    @Override public boolean isEnabled() { return true; }
}
