package com.example.demo.dto;

public class ResourceDTO {
	private Integer id;
	private String name;
	private int availableQuantity;

	public ResourceDTO() {}

	public ResourceDTO(Integer id, String name, int availableQuantity) {
		this.id = id;
		this.name = name;
		this.availableQuantity = availableQuantity;
	}

	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public int getAvailableQuantity() { return availableQuantity; }
	public void setAvailableQuantity(int availableQuantity) { this.availableQuantity = availableQuantity; }
}
