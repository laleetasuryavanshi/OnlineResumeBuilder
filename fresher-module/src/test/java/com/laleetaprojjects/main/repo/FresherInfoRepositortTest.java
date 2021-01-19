package com.laleetaprojjects.main.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
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
public class FresherInfoRepositortTest {

	@MockBean
	private FresherModuleRepository freshrepo;
	public FresherInfo finfo;

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
		freshrepo.save(finfo);
		// when(freshrepo.save(finfo)).thenReturn(finfo);
		// when(repo.findById(finfo.getFresherId())).thenReturn(Optional.of(finfo));

	}

	@Test
	public void test_saveFresherInfo() {
		when(freshrepo.save(finfo)).thenReturn(finfo);
		FresherInfo fresh = freshrepo.save(finfo);
		assertThat(fresh.getFresherId()).isGreaterThan(0);
	}

	@Test
	public void test_getAllFresher() {
		when(freshrepo.findAll())
				.thenReturn(Stream.of(new FresherInfo("Laleeta", "las@gmail.com", "8897645332", 22, "pune"))
						.collect(Collectors.toList()));
		List<FresherInfo> freshers = (List<FresherInfo>) freshrepo.findAll();
		assertThat(freshers.size()).isGreaterThan(0);
	}

	@Test
	public void test_getFresherById() {
		when(freshrepo.findById(finfo.getFresherId())).thenReturn(Optional.of(finfo));
		Optional<FresherInfo> f = freshrepo.findById(finfo.getFresherId());
		FresherInfo f2 = f.get();
		assertEquals(finfo, f2);

	}

	@Test
	public void test_deleteFresherById() {
		long id = finfo.getFresherId();
		doNothing().when(freshrepo).deleteById(id);
		Optional<FresherInfo> fresh = freshrepo.findById(id);
		System.out.println(fresh);

	}
}
