package com.libaryApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libaryApplication.entity.Edition;
@Repository
public interface EditionRepo extends JpaRepository<Edition,Integer> {

}
