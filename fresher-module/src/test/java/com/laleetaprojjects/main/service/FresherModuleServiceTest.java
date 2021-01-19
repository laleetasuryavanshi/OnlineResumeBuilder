package com.laleetaprojjects.main.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.laleetaprojjects.main.controller.FresherModuleController;
import com.laleetaprojjects.main.entity.AcademicInfo;
import com.laleetaprojjects.main.entity.FresherInfo;
import com.laleetaprojjects.main.entity.Hobbies;
import com.laleetaprojjects.main.entity.Projects;
import com.laleetaprojjects.main.entity.SkillInfo;
import com.laleetaprojjects.main.exception.FresherNotFoundException;
import com.laleetaprojjects.main.repo.FresherModuleRepository;
import com.laleetaprojjects.main.service.FresherModuleServiceImpl;

@SpringBootTest
public class FresherModuleServiceTest {

	@Autowired
	private FresherModuleServiceImpl fservice;
	@MockBean
	private FresherModuleRepository freshrepo;
	FresherInfo finfo;

	@Test
	public void test_getAllFresher() {

		when(freshrepo.findAll())
				.thenReturn(Stream.of(new FresherInfo("harshi", "harshi@gmail.com", "8809765443", 21, "pune"))
						.collect(Collectors.toList()));
		String msg = fservice.findAllFresher();
		Assertions.assertEquals("Get all fresher list", msg);
	}

	@BeforeEach
	@Test
	public void test_saveFresherInfo() {
		finfo = new FresherInfo(121L, "harshi", "harshi@gmail.com", "8809765443", 21, "pune");
		AcademicInfo academic = new AcademicInfo("60.8", "54", "btech", "it", "89", 0, 0);
		Hobbies h = new Hobbies("singhing");
		Projects p = new Projects("ML", 2019);
		SkillInfo s = new SkillInfo("hibernate");
		List<SkillInfo> slist = new ArrayList<>();
		slist.add(s);
		List<Hobbies> hlist = new ArrayList<>();
		hlist.add(h);
		List<Projects> plist = new ArrayList<>();
		plist.add(p);
		finfo.setAcademic(academic);
		finfo.setSkills(slist);
		finfo.setProjects(plist);
		finfo.setHobbies(hlist);

		when(freshrepo.save(finfo)).thenReturn(finfo);
		String f2 = fservice.saveFresherInfo(finfo);

		Assertions.assertEquals("Save Fresher Successfully with id 121", f2);
	}

	@Test
	public void test_findFresherById() {
		when(freshrepo.findById(finfo.getFresherId())).thenReturn(Optional.of(finfo));
		String f2 = fservice.findFresherById(finfo.getFresherId());
		Assertions.assertEquals("Find Fresher with Id 121", f2);
	}

	@Test
	public void test_deleteFresherById() {
		when(freshrepo.findById(finfo.getFresherId())).thenReturn(Optional.of(finfo));
		String f2 = fservice.deleteFresherById(finfo.getFresherId());
		Assertions.assertEquals("Delete Success with id 121", f2);
	}
}
