package com.laleetaprojjects.main.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;

import com.laleetaprojjects.main.entity.FresherInfo;
import com.laleetaprojjects.main.entity.Hobbies;
import com.laleetaprojjects.main.entity.Projects;
import com.laleetaprojjects.main.entity.SkillInfo;
import com.laleetaprojjects.main.exception.FresherNotFoundException;

import com.laleetaprojjects.main.repo.FresherModuleRepository;

import antlr.collections.List;

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

}
