package com.example.demo.service;

import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import com.example.demo.entities.Experienced;

public interface IExperiencedService {
	public Experienced getExperiencedById(Integer id);
	public Experienced addExperienced(Experienced experienced);
	public List<Experienced> getAllExperienced();
	public void deleteExperiencedById(Integer id);
	public ResponseEntity<InputStreamResource> generatePdfByUserId(Integer id,String template);
	
}
