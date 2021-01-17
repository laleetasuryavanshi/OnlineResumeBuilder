package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.FresherModuleRepository;
import com.example.demo.dao.RegistrationRepo;
import com.example.demo.entities.AcademicInfo;
import com.example.demo.entities.FresherInfo;
import com.example.demo.entities.Hobbies;
import com.example.demo.entities.Projects;
import com.example.demo.entities.Registration;
import com.example.demo.entities.SkillInfo;
import com.example.demo.exception.FresherNotFoundException;
import com.example.demo.service.FresherModuleServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(description = "This is Fresher Controller Responsible For Performing Different Database Operations")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Validated
@RequestMapping(value = "/fresher")
public class FresherModuleController {
	@Autowired
	private FresherModuleServiceImpl freshservice;
	@Autowired
	private FresherModuleRepository repo;
	
	@Autowired
	private RegistrationRepo register;

	@ApiOperation("This will  save all Fresher Objects")
	@PostMapping(value = "/save")
	public String savFresherInfo(@Valid @RequestBody FresherInfo freshinfo) {
		return freshservice.saveFresherInfo(freshinfo);

	}

	@GetMapping(value = "/fresh/{id}")
	public String findFresherById(@PathVariable long id) {
		repo.findById(id).orElseThrow(() -> new FresherNotFoundException("No Fresher Found with id" + " " + id));
		return freshservice.findFresherById(id);

	}

	@GetMapping(value = "/fresh")
	public String findAllFresher() {
		return freshservice.findAllFresher();

	}

	@DeleteMapping("/deletefresher/{id}")
	public String deleteFresherById(@ApiParam("Need To Provide Fresher Id In The Form of Long") @PathVariable Long id) {
		repo.findById(id).orElseThrow(() -> new FresherNotFoundException("No Fresher Found with id" + " " + id));

		return freshservice.deleteFresherById(id);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<FresherInfo> getFresherById(@PathVariable long id) {
		Optional<FresherInfo> fresherInfo = repo.findById(id);
		if (fresherInfo.isPresent()) {
			return ResponseEntity.ok(fresherInfo.get());
		} else {			
			throw new FresherNotFoundException("No Fresher Found with id" + " " + id);
		}
	}

	@ApiOperation("This will  give all Fresher Objects")
	@GetMapping
	public List<FresherInfo> getAllFresher() {
		return (List<FresherInfo>) repo.findAll();
	}

	// Registration

	@Autowired
	private RegistrationRepo rrepo;
	
	@GetMapping(value = "/{pass}/{user}")
	public  String logIn(@PathVariable String pass, @PathVariable String user) {

		List<Registration> entries = (List) rrepo.findAll();
		boolean check = true;
		String ans;
		for (Registration entry : entries) {
			if (entry.getPassword().equals(pass) && entry.getUsername().equals(user)) {
				check = false;
				break;
			}
		}
		if (check == true) {
			ans = "Enter Correct Password,Username.. If new user ===>Create account...=> Register first";
		}

		else {
			ans = "Login Successfully";
		}
		return ans;
		
      //return entries;
	}

	@PostMapping("/registration")
	// return 201 instead of 200
	@ResponseStatus(HttpStatus.CREATED)
	String saveRegistration(@Valid @RequestBody Registration regis) {
		List<Registration> entries = (List) rrepo.findAll();
		boolean check = true;
		String ans;
		for (Registration entry : entries) {
			if ( entry.getUsername().equals(regis.getUsername())) {
				check = false;
				break;
			}
		}
		if (check == true) {
			 rrepo.save(regis);
			ans = "Register Succesfully";
		}

		else {
			ans = "Username already in record:(!! kindly enter other combinations";
		}
		return ans;
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<FresherInfo> updateEmployee(@PathVariable Long id,
			@RequestBody FresherInfo fresher) {
		FresherInfo f = repo.findById(id).get();
		f.setFresherName(fresher.getFresherName());
		f.setEmail(fresher.getEmail());
		f.setContactNo(fresher.getContactNo());
		f.setAge(fresher.getAge());
		f.setAddress(fresher.getAddress());
		
		
		
		
		
		List<SkillInfo> skills = f.getSkills();
		for (SkillInfo s1 : skills) {
			for (SkillInfo s2 : fresher.getSkills()) {
				s1.setSkillName(s2.getSkillName());
				s1.setFresherinfo(f);
			}
			
		}
		 AcademicInfo ac=f.getAcademic();
		 
		 ac.setHscPercent(fresher.getAcademic().getHscPercent());
		 ac.setSscPercent(fresher.getAcademic().getSscPercent());
		 ac.setBranch(fresher.getAcademic().getBranch());
		 ac.setDegree(fresher.getAcademic().getDegree());
		 ac.setDegreePercent(fresher.getAcademic().getDegreePercent());
		 ac.setNoOfBacklogs(fresher.getAcademic().getNoOfBacklogs());
		 ac.setYeargap(fresher.getAcademic().getYeargap());
		 List<Hobbies> hobbies = f.getHobbies();
			for (Hobbies h1 : hobbies) {
				for (Hobbies h2 : fresher.getHobbies()) {
					h1.setHobbyName(h2.getHobbyName());
					h1.setFresherinfo(f);
					
				}
				
			}

			
			
		List<Projects> projects = f.getProjects();
		for (Projects p1 : projects) {
			for (Projects p2 : fresher.getProjects()) {
				p1.setProjectName(p2.getProjectName());
				p1.setYear(p2.getYear());
				p1.setFresherinfo(f);
			}
			
		}
         
		
		
		
		FresherInfo updatedfresher = repo.save(f);
		return ResponseEntity.ok(updatedfresher);
	}
	
	@ApiOperation("This will generate resume pdf.")
	   @GetMapping(value = "/download/{id}/{template}", produces = MediaType.APPLICATION_PDF_VALUE)
		public ResponseEntity<InputStreamResource> generatePdfByUserId(@PathVariable Long id, @PathVariable String template) 
	   {
			return freshservice.generatePdfByUserId(id, template);
	   }
	
	@GetMapping(value="/allusers")
	public List<Registration> getAllUsers() {
		return  register.findAll();
	}
	 @DeleteMapping("/deleteuser/{username}")
		public ResponseEntity<String> deleteUser(@PathVariable String username){
		  register.deleteById(username);
			 return new ResponseEntity<>("Deleted!!!", HttpStatus.OK);
		}
	
	
	@PatchMapping("/forgetpass")
	public ResponseEntity<Registration> updateFresherPass(
	@RequestBody Registration registration) {
		      String username=registration.getUsername();
		      Optional <Registration> r=rrepo.findByUsername(username);
				if (r.isPresent()) {
					Registration re=rrepo.findByUsername(username).get();
					 re.setPassword(registration.getPassword());
					 Registration regis=rrepo.save(re);
					 return ResponseEntity.ok(re);
				
				} else {			
					throw new FresherNotFoundException("No Fresher Found with Username" + " " + username);
				}
				
		
			}
	

}
