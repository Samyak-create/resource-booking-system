package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Resource {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
	private String name;
	private int totalQuantity;
	private int availableQuantity;

	public Resource() {}

	public Resource(Integer id, String name, int totalQuantity, int availableQuantity) {
		this.id = id;
		this.name = name;
		this.totalQuantity = totalQuantity;
		this.availableQuantity = availableQuantity;
	}

	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public int getTotalQuantity() { return totalQuantity; }
	public void setTotalQuantity(int totalQuantity) { this.totalQuantity = totalQuantity; }
	public int getAvailableQuantity() { return availableQuantity; }
	public void setAvailableQuantity(int availableQuantity) { this.availableQuantity = availableQuantity; }
}
