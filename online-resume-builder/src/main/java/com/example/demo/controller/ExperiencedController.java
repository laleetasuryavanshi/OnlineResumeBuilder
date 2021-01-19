package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ExperiencedRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.entities.Education;
//import com.dao.IExperiencedDAOImpl;
import com.example.demo.entities.Experienced;
import com.example.demo.entities.Project;
import com.example.demo.entities.Skill;
import com.example.demo.entities.User;
import com.example.demo.entities.WorkEx;
import com.example.demo.exception.ExperiencedUserNotFoundException;
import com.example.demo.exception.ResumeNotFoundException;
import com.example.demo.service.ExperiencedServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "This is Experienced Controller Responsible For Performing Different Database Operations.")
@CrossOrigin(origins="http://localhost:3000")
@RestController
@Validated
@RequestMapping(value = "/resume")
public class ExperiencedController {

	@Autowired
	private ExperiencedRepository experiencedRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ExperiencedServiceImpl service;
	
	@PostMapping(value="/register")
	public ResponseEntity<String> registration(@RequestBody User user)
	{
		List<User> entries=(List)userRepository.findAll();
		for(User entry:entries)
		{
			if(entry.getUserId().equals(user.getUserId()))
			{
				return new ResponseEntity<String>("User Id is already present. Please enter another User Id",HttpStatus.BAD_REQUEST);			
			}
		}
		userRepository.save(user);
		return new ResponseEntity<String>("Registration completed successfully.",HttpStatus.OK);
	}
	
	@GetMapping(value="/login/{userId}/{password}")
	public String login(@PathVariable String userId, @PathVariable String password)
	{
		List<User> entries=(List)userRepository.findAll();
		for(User entry:entries)
		{
			if(entry.getUserId().equals(userId) && entry.getPassword().equals(password))
			{
				return "Login successfull.";
			}
		}
		return "User Id or password is incorrect.";
	}
	
	/*
	 * @GetMapping(value = "/getByUser/{id}/{password}") public
	 * ResponseEntity<Experienced> getExperiencedByUserId(@PathVariable String
	 * id, @PathVariable String password) { //experiencedRepository.findByUser(id).
	 * //orElseThrow(()->new ResumeNotFoundException("Resume with ID "
	 * +id+" is not available. Please enter correct ID.")); User user=new
	 * User(id,password); Experienced experienced =
	 * (experiencedRepository.findByUser(user)).get(); return new
	 * ResponseEntity<Experienced>(experienced, HttpStatus.OK);
	 */
	//}

	@ApiOperation("This will get an existing resume by its id.")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Experienced> getExperiencedById(@PathVariable Integer id) {
		experiencedRepository.findById(id).
		orElseThrow(()->new ResumeNotFoundException("Resume with ID "+id+" is not available. Please enter correct ID."));
		Experienced experienced = service.getExperiencedById(id);
		return new ResponseEntity<Experienced>(experienced, HttpStatus.OK);
	}

	@ApiOperation("This will add a new resume.")
	@PostMapping(value = "addExperienced")
	public ResponseEntity<String> addExperienced(@Valid @RequestBody Experienced experienced) {
		Experienced experienced1 = service.addExperienced(experienced);
		int id = experienced1.getId();
		return new ResponseEntity<String>("Resume With ID :" + id + " Created Successfully. Please note it down to view and download this resume.", HttpStatus.OK);
	}

	@ApiOperation("This will give all Experienced Resume in the form of List.")
	@GetMapping(value = "getAllExperienced")
	public List<Experienced> getAllExperienced() {
		// return (List<Experienced>) repository.findAll();
		return service.getAllExperienced();
	}

	@ApiOperation("This will delete a resume by its id.")
	@DeleteMapping("/deleteExperiencedById/{id}")
	public ResponseEntity<String> deleteExperiencedById(@PathVariable Integer id) {
		experiencedRepository.findById(id).
		orElseThrow(()->new ResumeNotFoundException("Resume with ID "+id+" is not available. Please enter correct ID."));
		service.deleteExperiencedById(id);
		return new ResponseEntity<String>("Resume With ID :" + id + " Deleted", HttpStatus.OK);

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Experienced> updateExperienced(@PathVariable Integer id,
			@Valid @RequestBody Experienced experienced) {
		Experienced e = experiencedRepository.findById(id).orElseThrow(() -> new ResumeNotFoundException(
				"Resume with ID " + id + " is not available. Please enter correct ID."));
		e.setId(e.getId());
		e.setFirstName(experienced.getFirstName());
		e.setLastName(experienced.getLastName());
		e.setEmail(experienced.getEmail());
		e.setPhone(experienced.getPhone());
		e.setCity(experienced.getCity());
		e.setState(experienced.getState());
		e.setPincode(experienced.getPincode());
		WorkEx w = e.getWorkex();
		w.setWid(e.getWorkex().getWid());
		w.setEmployerName(experienced.getWorkex().getEmployerName());
		w.setFromDate(experienced.getWorkex().getFromDate());
		w.setJobTitle(experienced.getWorkex().getJobTitle());
		w.setToDate(experienced.getWorkex().getToDate());
		w.setWcity(experienced.getWorkex().getWcity());
		w.setWstate(experienced.getWorkex().getWstate());
		e.setWorkex(w);
		w.setExperienced(e);
		Education ed = e.getEducation();
		ed.setEid(e.getEducation().getEid());
		ed.setDegreeName(experienced.getEducation().getDegreeName());
		ed.setDegreePercent(experienced.getEducation().getDegreePercent());
		ed.setEndDate(experienced.getEducation().getEndDate());
		ed.setFieldOfStudy(experienced.getEducation().getFieldOfStudy());
		ed.setHsc(experienced.getEducation().getHsc());
		ed.setSsc(experienced.getEducation().getSsc());
		ed.setStartDate(experienced.getEducation().getStartDate());
		e.setEducation(ed);
		ed.setExperienced(e);
		Set<Project> projects = e.getProjects();
		for (Project p1 : projects) {
			for (Project p2 : experienced.getProjects()) {
					p1.setProjects(p2.getProjects());
					p1.setExperienced(e);
			}
		}
		Set<Skill> skills = e.getSkills();
		for (Skill s1 : skills) {
			for (Skill s2 : experienced.getSkills()) {
				s1.setSkills(s2.getSkills());
			}
			s1.setExperienced(e);
		}

		Experienced updatedExperience = experiencedRepository.save(e);
		return ResponseEntity.ok(updatedExperience);
	}
	
	@ApiOperation("This will generate resume pdf.")
	@GetMapping(value = "/{id}/{template}", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> generatePdfByUserId(@PathVariable Integer id,
			@PathVariable String template) {
		return service.generatePdfByUserId(id, template);

	}
	
	@DeleteMapping("/delete/{username}")
	public ResponseEntity<String> deleteUser(@PathVariable String username){
	  userRepository.deleteById(username);
		 return new ResponseEntity<>("Deleted!!!", HttpStatus.OK);
	}
    
	@GetMapping(value="/userslist")
	public Iterable<User> getallUsers()
	{
		return userRepository.findAll();
	}
	
	@PatchMapping("/exforgetpass")
	public ResponseEntity<User> updateExperiencedPass(
	@RequestBody User user) {
		      String userId=user.getUserId();
		      Optional <User> u=userRepository.findById(userId);
				if (u.isPresent()) {
					User us=userRepository.findById(userId).get();
					 us.setPassword(user.getPassword());
					 User usr=userRepository.save(us);
					 return ResponseEntity.ok(us);
				
				} else {			
					throw new ExperiencedUserNotFoundException
					("No User Found with User Id" + " " + userId);
				}
				
		
			}

    }

    