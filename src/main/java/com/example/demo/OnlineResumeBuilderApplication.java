package com.example.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.dao.ExperiencedRepository;
import com.example.demo.entities.Education;
import com.example.demo.entities.Experienced;
import com.example.demo.entities.Project;
import com.example.demo.entities.Skill;
import com.example.demo.entities.WorkEx;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class OnlineResumeBuilderApplication {

	@Autowired
	private ExperiencedRepository repository;
	
	/*
	 * @Override public void run(String... args) throws Exception { Experienced
	 * e=new Experienced(); e.setFirstName("Aadesh"); e.setLastName("Puthran");
	 * e.setPhone("9876543210"); e.setCity("Mumbai"); e.setState("MAH");
	 * e.setPincode(12345); e.setEmail("ap@gmail.com"); SimpleDateFormat dateFormat
	 * = new SimpleDateFormat("dd-MMM-yyyy"); Date from_date = new Date(); try { //
	 * Parsing the String from_date = dateFormat.parse("12-JAN-2014"); } catch
	 * (ParseException ex) { } Date to_date = new Date(); try { // Parsing the
	 * String from_date = dateFormat.parse("12-JAN-2017"); } catch (ParseException
	 * ex) { } WorkEx we=new
	 * WorkEx("Programmer","Amazon","Mumbai","MAH",from_date,to_date); Date s_date =
	 * new Date(); try { // Parsing the String from_date =
	 * dateFormat.parse("12-JAN-2004"); } catch (ParseException ex) { } Date e_date
	 * = new Date(); try { // Parsing the String from_date =
	 * dateFormat.parse("12-JAN-2010"); } catch (ParseException ex) { } Education
	 * ed=new Education(85,72,"Engg",67,"Computer",s_date,e_date); Project p1=new
	 * Project("Shopping website"); Project p2=new Project("Resume Builder");
	 * e.getProjects().add(p1); e.getProjects().add(p2); Skill s1=new Skill("Java");
	 * Skill s2=new Skill("Python"); e.getSkills().add(s1); e.getSkills().add(s2);
	 * e.setWorkex(we); e.setEducation(ed); we.setExperienced(e);
	 * ed.setExperienced(e); s1.setExperienced(e); s2.setExperienced(e);
	 * p1.setExperienced(e); p2.setExperienced(e); repository.save(e);
	 */
	 
	public static void main(String[] args) {
		SpringApplication.run(OnlineResumeBuilderApplication.class, args);
	}

}
