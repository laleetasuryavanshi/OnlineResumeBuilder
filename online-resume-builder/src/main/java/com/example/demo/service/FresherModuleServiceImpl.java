package com.example.demo.service;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dao.FresherModuleRepository;
import com.example.demo.entities.FresherInfo;
import com.example.demo.entities.Hobbies;
import com.example.demo.entities.Projects;
import com.example.demo.entities.SkillInfo;
import com.example.demo.entities.TemplateType;
import com.example.demo.exception.TemplateNotFoundException;
import com.example.demo.util.GenerateFresherResumePdf;

@Service
public class FresherModuleServiceImpl implements FresherModuleService {

	@Autowired
	private FresherModuleRepository freshrepo;

	@Override
	public String saveFresherInfo(FresherInfo freshinfo) {

		java.util.List<SkillInfo> skills = freshinfo.getSkills();

		for (SkillInfo s : skills) {
			s.setFresherinfo(freshinfo);
		}

		freshinfo.getAcademic().setFresherinfo(freshinfo);
		java.util.List<Hobbies> hobbies = freshinfo.getHobbies();
		for (Hobbies h : hobbies) {
			h.setFresherinfo(freshinfo);
		}
		java.util.List<Projects> projects = freshinfo.getProjects();
		for (Projects p : projects) {
			p.setFresherinfo(freshinfo);
		}
		FresherInfo finfo = freshrepo.save(freshinfo);
		return "Information saved succefully.....kindly note down application id" + " " + finfo.getFresherId();

	}

	@Override
	public String findFresherById(long id) {
		FresherInfo finfo = freshrepo.findById(id).get();
		return "Find Fresher with Id" + " " + id;

	}

	@Override
	public String deleteFresherById(Long id) {
		freshrepo.deleteById(id);
		return "Delete Success with id" + " " + id;
	}

	@Override
	public String findAllFresher() {
		Iterable<FresherInfo> list = freshrepo.findAll();
		return "Get all fresher list";
	}
	
	@Override
	public ResponseEntity<InputStreamResource> generatePdfByUserId(Long id, String template) {
		FresherInfo fresher = freshrepo.findById(id).get();
		TemplateType temp = new TemplateType(template);
		ByteArrayInputStream bis=null; 
		if(temp.getTemplate().equals("LA"))
		{
		 bis = GenerateFresherResumePdf.letfAlignment(fresher);
		}
		else if(temp.getTemplate().equals("CA"))
		{
		 bis = GenerateFresherResumePdf.centerAlignment(fresher);
		}
		else
		{
			bis = GenerateFresherResumePdf.rightAlignment(fresher);
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
