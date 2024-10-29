package com.libaryApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libaryApplication.entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book,Integer> {

}
