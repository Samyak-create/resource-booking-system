package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ResourceDTO;
import com.example.demo.entities.Resource;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repo.ResourceRepo;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	 private ResourceRepo repo;
	@Override
	public String addResource(ResourceDTO resDto) {
		Resource res=new Resource();
		
		res.setName(resDto.getName());
		res.setAvailableQuantity(resDto.getAvalilableQuantity());
		
		res.setTotalQuantity(resDto.getAvalilableQuantity());
		
		repo.save(res);
		return "Resource Addedd Succesfully";
	}

	@Override
	public String deleteResource(int id ) {
		
		Resource res=repo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Resource Not found with id "+id));
		repo.delete(res);
		return "Resource Deleted Succesfully";
	}

	

	
	

	@Override
	public ResourceDTO findByName(String name) {
		
		Resource res=repo.findByName(name)
				.orElseThrow(()->new ResourceNotFoundException("Resource Not found with name : "+name));
		      ResourceDTO dto=new ResourceDTO();
		      dto.setId(res.getId());
		      dto.setName(res.getName());
		      dto.setAvalilableQuantity(res.getAvailableQuantity());
		      
		
	return 	dto;
		
	}

	@Override
	public Resource findById(int id) {
		// TODO Auto-generated method stub
		
		Resource res=repo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Resource not found with id :"+id));
		return res;
	}

	@Override
	public List<ResourceDTO> getAllResources() {
		
		List<Resource> resources=repo.findAll();
		
		List<ResourceDTO> dtoList=new ArrayList<>();
		
		for(Resource res:resources) {
			ResourceDTO dto=new ResourceDTO();
			dto.setId(res.getId());
			dto.setName(res.getName());
			dto.setAvalilableQuantity(res.getAvailableQuantity());
			
			dtoList.add(dto);
		}
		
		return dtoList;
	}

}
