package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entities.Experienced;
import com.example.demo.exception.ResumeNotFoundException;

@SpringBootTest
class OnlineResumeBuilderApplicationTests {


	
	/*@Test
	@Disabled
	public void testGetAllExperienced()
	{
		
		List<Experienced> experienced=dao.getAllExperienced();
		
		assertEquals(2,experienced.size());	
		
	}
	
	@Test
	public void testGetExperiencedById()
	{
		
		Experienced experienced=dao.getExperiencedById(8);
		
		assertEquals(8,experienced.getId());	
		
	}
	
	@Test
	public void testDeleteExperiencedById()
	{
		dao.deleteExperiencedById(50);
		assertThrows(ResumeNotFoundException.class, ()->dao.getExperiencedById(50));
	}
}
*/
}