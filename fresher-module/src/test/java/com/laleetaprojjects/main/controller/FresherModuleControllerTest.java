package com.laleetaprojjects.main.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laleetaprojjects.main.FresherModule1ApplicationTests;
import com.laleetaprojjects.main.entity.AcademicInfo;
import com.laleetaprojjects.main.entity.FresherInfo;
import com.laleetaprojjects.main.entity.Hobbies;
import com.laleetaprojjects.main.entity.Projects;
import com.laleetaprojjects.main.entity.Registration;
import com.laleetaprojjects.main.entity.SkillInfo;
import com.laleetaprojjects.main.repo.FresherModuleRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class FresherModuleControllerTest {

	private static final ObjectMapper om = new ObjectMapper();
	public FresherInfo finfo;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private FresherModuleRepository repo;
	@Autowired
	private FresherModuleController fcontroller;

	@BeforeEach
	public void setUp() {

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
		when(repo.save(finfo)).thenReturn(finfo);
		when(repo.findById(finfo.getFresherId())).thenReturn(Optional.of(finfo));

	}

	@Test
	public void test_findFresherById() throws Exception {
		String uri = "/fresher/fresh/121";
		MockMvc mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		String content = mvcResult.getResponse().getContentAsString();
		Assertions.assertEquals("Find Fresher with Id 121", content);

	}

	@Test
	public void test_findAllFresher() throws Exception {
		String uri = "/fresher/fresh";
		MockMvc mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		String content = mvcResult.getResponse().getContentAsString();
		Assertions.assertEquals("Get all fresher list", content);
	}
	/*
	 * @Test public void test_saveFresherInfo() throws Exception {
	 * 
	 * MockMvc
	 * mvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build(); String
	 * uri = "/fresher/save"; MvcResult mvcResult =
	 * mvc.perform(MockMvcRequestBuilders.post(uri)).andReturn(); String content =
	 * mvcResult.getResponse().getContentAsString();
	 * 
	 * Assertions.assertEquals( "Save Fresher Successfully with id 121",content);
	 * 
	 * }
	 */

	@Test
	public void test_deleteFresherById() throws Exception {

		MockMvc mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		String uri = "/fresher/deletefresher/121";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();

		String content = mvcResult.getResponse().getContentAsString();

		Assertions.assertEquals("Delete Success with id 121", content);
	}

}
