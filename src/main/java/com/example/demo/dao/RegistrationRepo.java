package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.FresherInfo;
import com.example.demo.entities.Registration;

public interface RegistrationRepo extends JpaRepository<Registration, String> {
	Optional<Registration> findByUsername(String username);
}
