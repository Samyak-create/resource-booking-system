package com.example.demo.dto;

import lombok.Data;

@Data
public class BookingRequest {

	private Integer empId;
	private Integer resId;
	private Integer quantity;
}
