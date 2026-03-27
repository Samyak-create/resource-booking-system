package com.example.demo.entities;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
    
    @ManyToOne
    private Employee emp;
    
    @ManyToOne
    private Resource res;
    
    private int quantity;
    private LocalDate bookingDate;

	public Booking() {}

	public Booking(Integer id, Employee emp, Resource res, int quantity, LocalDate bookingDate) {
		this.id = id;
		this.emp = emp;
		this.res = res;
		this.quantity = quantity;
		this.bookingDate = bookingDate;
	}

	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	public Employee getEmp() { return emp; }
	public void setEmp(Employee emp) { this.emp = emp; }
	public Resource getRes() { return res; }
	public void setRes(Resource res) { this.res = res; }
	public int getQuantity() { return quantity; }
	public void setQuantity(int quantity) { this.quantity = quantity; }
	public LocalDate getBookingDate() { return bookingDate; }
	public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }
}
