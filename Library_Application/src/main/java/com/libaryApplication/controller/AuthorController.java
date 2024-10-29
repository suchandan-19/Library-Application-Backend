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

import com.libaryApplication.entity.Author;
import com.libaryApplication.service.AuthorService;

@RestController
@RequestMapping("/api/author")
public class AuthorController {
	
	@Autowired
	private AuthorService authService;
	
	@GetMapping
	public ResponseEntity<List<Author>> listAuthors(){
		List<Author> authors= authService.getAllAuthor();
		return ResponseEntity.ok(authors);
	}
	
	@GetMapping("/{id}")
	public  ResponseEntity<Author> getAuthors(@PathVariable int id){
		Author author = authService.getAuthorById(id);
		if(author==null) {
			ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(author);
	}
	
	@PostMapping
		public ResponseEntity<Author> saveAuthor(@RequestBody Author author){
		Author createAuthors = authService.saveOrUpdateAuthor(author);	
		return ResponseEntity.status(HttpStatus.CREATED).body(createAuthors);
		}
	
	@PutMapping("/{id}")
	public ResponseEntity<Author> updateAuthor(@PathVariable int id, @RequestBody Author author){
		Author existingAuthor=authService.getAuthorById(id);
		if(existingAuthor==null) {
			return ResponseEntity.notFound().build();
		}
		author.setId(id);
		authService.saveOrUpdateAuthor(author);
		return ResponseEntity.ok(author);
		
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAuthor(@PathVariable int id){
		Author author = authService.getAuthorById(id);
		if(author==null) {
			ResponseEntity.notFound().build();
		}
		authService.deleteAuthorById(id);
		return ResponseEntity.noContent().build();
	}

}
