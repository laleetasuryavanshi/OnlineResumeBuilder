package com.example.demo.service;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ExperiencedRepository;
import com.example.demo.entities.Experienced;
import com.example.demo.entities.TemplateType;
import com.example.demo.entities.Project;
import com.example.demo.entities.Skill;
import com.example.demo.exception.ResumeNotFoundException;
import com.example.demo.exception.TemplateNotFoundException;
import com.example.demo.util.GenerateExperiencedResumePdf;

@Service
public class ExperiencedServiceImpl implements IExperiencedService {
	
	@Autowired
	private ExperiencedRepository experiencedRepository;
	
	public Experienced getExperiencedById(Integer id)
	{
		Experienced experienced=experiencedRepository.findById(id).
				orElseThrow(()->new ResumeNotFoundException("Resume with ID "+id+" is not available. Please enter correct ID."));
				return experienced;
	}
	
	public Experienced addExperienced(Experienced experienced)
	{
		experienced.getWorkex().setExperienced(experienced);
		experienced.getEducation().setExperienced(experienced);
		Set<Skill> skills=experienced.getSkills();
		for (Skill s1 : skills) {
			s1.setExperienced(experienced);
		}
		Set<Project> projects=experienced.getProjects();
		for (Project p1 : projects) {
			p1.setExperienced(experienced);
		}
		return experiencedRepository.save(experienced);
	}
	
	public List<Experienced> getAllExperienced()
	{
		return (List<Experienced>) experiencedRepository.findAll();
	}
	
	public void deleteExperiencedById(Integer id)
	{
		experiencedRepository.findById(id).
		orElseThrow(()->new ResumeNotFoundException("Resume with ID "+id+" is not available. Please enter correct ID."));
		experiencedRepository.deleteById(id);
	}
	
	@Override
	public ResponseEntity<InputStreamResource> generatePdfByUserId(Integer id,String template) {
		Experienced experienced=experiencedRepository.findById(id).
				orElseThrow(()->new ResumeNotFoundException("Resume with ID "+id+" is not available. Please enter correct ID."));
		TemplateType temp = new TemplateType(template);
		ByteArrayInputStream bis=null;
		if (temp.getTemplate().equals("LA")) {
			bis = GenerateExperiencedResumePdf.leftAlignment(experienced);
		} else if (temp.getTemplate().equals("CA")) {
			bis = GenerateExperiencedResumePdf.centerAlignment(experienced);
		} else if (temp.getTemplate().equals("RA")){
			bis = GenerateExperiencedResumePdf.rightAlignment(experienced);
		}
		if(bis==null)
		{
		 throw new TemplateNotFoundException("Template "+temp.getTemplate()+" is not present in our application");
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=resume.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}
}
