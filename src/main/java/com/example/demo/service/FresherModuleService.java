package com.example.demo.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import com.example.demo.entities.FresherInfo;

public interface FresherModuleService {
	public String saveFresherInfo(FresherInfo freshinfo);
	public String findFresherById(long id);
	public String deleteFresherById(Long id);
	public String findAllFresher();
	public ResponseEntity<InputStreamResource> generatePdfByUserId(Long id, String template);
}
