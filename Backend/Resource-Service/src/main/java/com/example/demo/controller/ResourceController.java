package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResourceDTO;
import com.example.demo.entities.Resource;
import com.example.demo.service.ResourceService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/resources")
public class ResourceController {
	@Autowired
	private ResourceService service;
	
	@GetMapping
	public List<ResourceDTO> allResources(){
		return service.getAllResources();
	}
	
	@GetMapping("/name/{name}")
	public ResourceDTO findByName(@PathVariable String name) {
	    return service.findByName(name);
	}
	

	@PostMapping
	public String addResource( @RequestBody ResourceDTO res) {
		
		return service.addResource(res);
	}
	
	
	
	@DeleteMapping("delete/{id}")
	public String deleteResource(@PathVariable int id) {
		return service.deleteResource(id);
	}
	
}
