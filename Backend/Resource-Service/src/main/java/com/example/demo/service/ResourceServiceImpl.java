package com.example.demo.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Resource;
import com.example.demo.repo.ResourceRepo;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	 private ResourceRepo repo;
	@Override
	public String addResource(Resource res) {
		repo.save(res);
		return "Resource Addedd Succesfully";
	}

	@Override
	public String deleteResource(Resource res) {
		repo.delete(res);
		return "Resource Deleted Succesfully";
	}

	@Override
	public List<Resource> availableResource() {
		
		
		return repo.findByBookedFalse();
	}

	@Override
	public String updateResource(int id) {
		Resource res=repo.findById(id);
		res.setBooked(true);
		repo.save(res);
		return "Booked Succesfully";
	}

	@Override
	public Resource findByName(String name) {
	return 	repo.findByName(name);
		
	}

	@Override
	public Resource findById(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public List<Resource> getAllResources() {
		
		return repo.findAll();
	}

}
