package com.libaryApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.libaryApplication.entity.Edition;
import com.libaryApplication.service.EditionService;

@RestController
@RequestMapping("/api/edition")
public class EditionController {
		
		@Autowired
		private EditionService editionService;
		
		@GetMapping
		public ResponseEntity<List<Edition>> listEditions(){
			List<Edition> editions= editionService.getAllEdition();
			return ResponseEntity.ok(editions);
		}
		
		@GetMapping("/{id}")
		public  ResponseEntity<Edition> getEditionss(@PathVariable int id){
			Edition edition = editionService.getEditionById(id);
			if(edition==null) {
				ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(edition);
		}
		
		@PostMapping
			public ResponseEntity<Edition> saveBook(@RequestBody Edition edition){
			Edition createEditions = editionService.saveOrUpdateEdition(edition);
			return ResponseEntity.status(HttpStatus.CREATED).body(createEditions);
			}
		
		@PutMapping("/{id}")
		public ResponseEntity<Edition> updateBook(@PathVariable int id, @RequestBody Edition edition){
			Edition existingEdition=editionService.getEditionById(id);
			if(existingEdition==null) {
				return ResponseEntity.notFound().build();
			}
			edition.setId(id);
			editionService.saveOrUpdateEdition(edition);
			return ResponseEntity.ok(edition);
			
			
		}
		@DeleteMapping("/{id}")
		public ResponseEntity<Edition> deleteBook(@PathVariable int id){
			Edition edition = editionService.getEditionById(id);
			if(edition==null) {
				ResponseEntity.notFound().build();
			}
			editionService.deleteEditionById(id);
			return ResponseEntity.noContent().build();
		}

}
