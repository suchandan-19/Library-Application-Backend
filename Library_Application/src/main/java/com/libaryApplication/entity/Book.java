package com.libaryApplication.entity;



import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name="books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String name;
	@ManyToMany
	@JoinTable(
			  name = "books_authors", 
			  joinColumns = {@JoinColumn(name = "book_id")}, 
			  inverseJoinColumns = {@JoinColumn(name = "author_id")})
	private List<Author> authors;
	
	@ManyToMany
	@JoinTable(
			  name = "books_editions", 
			  joinColumns = {@JoinColumn(name = "book_id")}, 
			  inverseJoinColumns = {@JoinColumn(name = "edition_id")})
	private List<Edition> editions;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public List<Edition> getEditions() {
		return editions;
	}

	public void setEditions(List<Edition> editions) {
		this.editions = editions;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
