package com.example.demo.service;

import java.util.List;

import com.example.demo.entities.Resource;

public interface ResourceService {
	
	List<Resource> getAllResources();
	
	String addResource(Resource res);
	
	String deleteResource(Resource res);
	
    List<Resource> availableResource();
    
    String updateResource(int id );
    
    Resource findByName(String name);
    
    Resource findById(int id);
}
