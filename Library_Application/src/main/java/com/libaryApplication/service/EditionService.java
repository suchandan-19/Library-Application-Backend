package com.libaryApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libaryApplication.entity.Edition;
import com.libaryApplication.repository.EditionRepo;

@Service
public class EditionService {
	
	@Autowired
	private EditionRepo editionRepo;
	
	public List<Edition> getAllEdition(){
		return editionRepo.findAll();
	}
	
	public Edition getEditionById(int id) {
		return editionRepo.findById(id).orElse(null);
	}
	
	public Edition saveOrUpdateEdition(Edition edition) {
		return editionRepo.save(edition);
	}
	
	public void deleteEditionById(int id) {
		editionRepo.findById(id).orElse(null);
		
		editionRepo.deleteById(id);
	}

}
