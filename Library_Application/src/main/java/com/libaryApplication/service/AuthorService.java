package com.libaryApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libaryApplication.entity.Author;
import com.libaryApplication.repository.AuthorRepo;

@Service
public class AuthorService {
	
	@Autowired
	private AuthorRepo authorRepo;
    
	
	public List<Author> getAllAuthor(){
		return authorRepo.findAll();
	}
	
	public Author getAuthorById(int id) {
		return authorRepo.findById(id).orElse(null);
	}
	
	public Author saveOrUpdateAuthor(Author author) {
		return authorRepo.save(author);
	}
	
	public void deleteAuthorById(int id) {
		authorRepo.findById(id).orElse(null);
		
		authorRepo.deleteById(id);
	}
}
