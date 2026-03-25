package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ResourceDTO;
import com.example.demo.entities.Resource;

public interface ResourceService {
	
	List<ResourceDTO> getAllResources();
	
	String addResource(ResourceDTO res);
	
	String deleteResource(int id);
	
    
    
   
    
    ResourceDTO findByName(String name);

	Resource findById(int id);
    
   
}
