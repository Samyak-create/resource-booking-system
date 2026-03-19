package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Resource;
import com.example.demo.service.ResourceService;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {
	@Autowired
	private ResourceService service;
	
	@GetMapping
	public List<Resource> allResources(){
		return service.getAllResources();
	}
	
	@GetMapping("/available")
	public List<Resource> availableResource(){
	  return service.availableResource();
	}

	@PostMapping
	public String addResource( @RequestBody Resource res) {
		
		return service.addResource(res);
	}
	
	@PutMapping("/book/{id}")
	public String updateResource(@PathVariable int id) {
		
		return service.updateResource(id);
	}
	
	@DeleteMapping
	public String deleteResource(@RequestBody Resource res) {
		return service.deleteResource(res);
	}
	
}
