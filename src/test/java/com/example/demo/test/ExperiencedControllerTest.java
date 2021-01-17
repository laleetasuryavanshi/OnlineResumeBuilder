package com.example.demo.test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.controller.ExperiencedController;
import com.example.demo.dao.ExperiencedRepository;
import com.example.demo.entities.Education;
import com.example.demo.entities.Experienced;
import com.example.demo.entities.Project;
import com.example.demo.entities.Skill;
import com.example.demo.entities.WorkEx;
import com.example.demo.service.ExperiencedServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;




@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class ExperiencedControllerTest {
	
	private static final ObjectMapper om = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ExperiencedRepository experiencedRepository;

	private static Experienced e;

	@Autowired
	private ExperiencedServiceImpl service;

	@BeforeEach
	public void createExperienced() {
		e = new Experienced("Ramesh", "Kumar", "9876543210", "Mumbai", "Maharashtra", 423151,
				"rk@gmail.com");
		e.setId(1);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Date from_date = new Date();
		try {
			from_date = dateFormat.parse("12-JAN-2014");
		} catch (ParseException ex) {

		}
		Date to_date = new Date();
		try { 
			to_date = dateFormat.parse("12-JAN-2017");
		} catch (ParseException ex) {
		}
		WorkEx we = new WorkEx("Programmer", "Amazon", "Mumbai", "MAH", from_date, to_date);
		Date s_date = new Date();
		try { 
			dateFormat.parse("12-JAN-2004");
		} catch (ParseException ex) {
		}
		Date e_date = new Date();
		try { 
			dateFormat.parse("12-JAN-2010");
		} catch (ParseException ex) {
		}
		Education ed = new Education(85, 72, "Engg", 67, "Computer", s_date, e_date);
		Project p1 = new Project("Shopping website");
		Project p2 = new Project("Resume Builder");
		e.getProjects().add(p1);
		e.getProjects().add(p2);
		Skill s1 = new Skill("Java");
		Skill s2 = new Skill("Python");
		e.getSkills().add(s1);
		e.getSkills().add(s2);
		e.setWorkex(we);
		e.setEducation(ed);
		we.setExperienced(e);
		ed.setExperienced(e);
		s1.setExperienced(e);
		s2.setExperienced(e);
		p1.setExperienced(e);
		p2.setExperienced(e);
		when(experiencedRepository.findById(e.getId())).thenReturn(Optional.of(e));
	}

	@Test
	public void test_getExperiencedById_OK() throws Exception {
		mockMvc.perform(get("/resume/1")).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.firstName", is("Ramesh")))
				.andExpect(jsonPath("$.lastName", is("Kumar")))
				.andExpect(jsonPath("$.phone", is("9876543210")))
				.andExpect(jsonPath("$.city", is("Mumbai")))
				.andExpect(jsonPath("$.state", is("Maharashtra")))
				.andExpect(jsonPath("$.pincode", is(423151)))
				.andExpect(jsonPath("$.email", is("rk@gmail.com")));
		verify(experiencedRepository, times(1)).findById(1);
	}

	
	
	
	
	  @Test public void find_ResumeIdNotFound_404() throws Exception {
	  mockMvc.perform(get("/resume/5")).andExpect(status().isNotFound()); }
	 
	 
	 

	@Test
	public void test_deleteExperiencedById_OK() throws Exception {

		doNothing().when(experiencedRepository).deleteById(1);

		mockMvc.perform(delete("/resume/deleteExperiencedById/1")).andDo(print()).andExpect(status().isOk());

		verify(experiencedRepository, times(1)).deleteById(1);
	}
	
	/*
	 * @Test public void test_addExperienced_OK() throws Exception { String
	 * URI="/resume/addExperienced"; String inputInJson=this.mapToJson(e);
	 * Mockito.when(experiencedRepository.save(Mockito.any(Experienced.class))).
	 * thenReturn(e); RequestBuilder
	 * requestBuilder=MockMvcRequestBuilders.post(URI).accept(MediaType.
	 * APPLICATION_JSON)
	 * .content(inputInJson).contentType(MediaType.APPLICATION_JSON); MvcResult
	 * result=mockMvc.perform(requestBuilder).andReturn();
	 * assertNotNull(HttpStatus.CREATED.value());
	 * 
	 * }
	 */
	
	@Test
    public void test_getAllExperienced_OK() throws Exception {
		List<Experienced> list=new ArrayList<>();
		list.add(e);

        when(experiencedRepository.findAll()).thenReturn(list);

        mockMvc.perform(get("/resume/getAllExperienced"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].firstName", is("Ramesh")))
				.andExpect(jsonPath("$[0].lastName", is("Kumar")))
				.andExpect(jsonPath("$[0].phone", is("9876543210")))
				.andExpect(jsonPath("$[0].city", is("Mumbai")))
				.andExpect(jsonPath("$[0].state", is("Maharashtra")))
				.andExpect(jsonPath("$[0].pincode", is(423151)))
				.andExpect(jsonPath("$[0].email", is("rk@gmail.com")));

        verify(experiencedRepository, times(1)).findAll();
    }
	
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
	
	
}
