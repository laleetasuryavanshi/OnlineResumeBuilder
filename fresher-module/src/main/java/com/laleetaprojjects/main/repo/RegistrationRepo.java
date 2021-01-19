package com.laleetaprojjects.main.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.laleetaprojjects.main.entity.Registration;

public interface RegistrationRepo extends JpaRepository<Registration, String> {
	Optional<Registration> findByUsername(String username);
}
