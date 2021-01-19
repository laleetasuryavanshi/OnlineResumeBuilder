package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.Experienced;
import com.example.demo.entities.User;

public interface ExperiencedRepository extends CrudRepository<Experienced,Integer>{
	//public Optional<Experienced> findByUser(User user);
}