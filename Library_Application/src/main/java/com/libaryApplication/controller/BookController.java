package com.libaryApplication.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import com.libaryApplication.entity.Book;
import com.libaryApplication.entity.Edition;
import com.libaryApplication.service.AuthorService;
import com.libaryApplication.service.BookService;
import com.libaryApplication.service.EditionService;

@RestController
@RequestMapping("/api/book")
public class BookController {
	
		@Autowired
		private BookService bookService;
		
		@Autowired
		private AuthorService authService;
		
		@Autowired
		private EditionService editionService;
		
		@GetMapping
		public ResponseEntity<List<Book>> listBooks(){
			List<Book> books= bookService.getAllBook();
			return ResponseEntity.ok(books);
		}
		
		@GetMapping("/{id}")
		public  ResponseEntity<Book> getBookss(@PathVariable int id){
			Book book = bookService.getBookById(id);
			if(book==null) {
				ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(book);
		}
		
		@PostMapping
			public ResponseEntity<Book> saveBook(@RequestBody Book book){
			List<Author> authors = new ArrayList<Author>();
			for(Author author:book.getAuthors()) {
				Author foundAuthor = authService.getAuthorById(author.getId());
				if(foundAuthor==null) {
					return ResponseEntity.notFound().build();
				}
				authors.add(foundAuthor);
			}
			book.setAuthors(authors);
			
			List<Edition> editions = new ArrayList<Edition>();
			for(Edition edition:book.getEditions()) {
				Edition foundEdition = editionService.getEditionById(edition.getId());
				if(foundEdition==null) {
					return ResponseEntity.notFound().build();
				}
				editions.add(foundEdition);
			}
			book.setEditions(editions);
			
			Book createBooks=bookService.saveOrUpdateBook(book);
			return ResponseEntity.status(HttpStatus.CREATED).body(createBooks);
			}
		
		@PutMapping("/{id}")
		public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book){
			Book existingBook=bookService.getBookById(id);
			if(existingBook==null) {
				return ResponseEntity.notFound().build();
			}
			
			List<Author> authors = new ArrayList<Author>();
			for(Author author:book.getAuthors()) {
				Author foundAuthor = authService.getAuthorById(author.getId());
				if(foundAuthor==null) {
					return ResponseEntity.notFound().build();
				}
				authors.add(foundAuthor);
			}
			book.setAuthors(authors);
			
			List<Edition> editions = new ArrayList<Edition>();
			for(Edition edition:book.getEditions()) {
				Edition foundEdition = editionService.getEditionById(edition.getId());
				if(foundEdition==null) {
					return ResponseEntity.notFound().build();
				}
				editions.add(foundEdition);
			}
			book.setEditions(editions);
			
			book.setId(id);
			bookService.saveOrUpdateBook(book);
			return ResponseEntity.ok(book);
			
			
		}
		@DeleteMapping("/{id}")
		public ResponseEntity<Void> deleteBook(@PathVariable int id){
			Book book = bookService.getBookById(id);
			if(book==null) {
				ResponseEntity.notFound().build();
			}
			bookService.deleteBookById(id);
			return ResponseEntity.noContent().build();
		}

}
