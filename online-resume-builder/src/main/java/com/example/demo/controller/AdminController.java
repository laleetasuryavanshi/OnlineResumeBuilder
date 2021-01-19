package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.AdminRepository;
//import com.cg.onlineResumeBuilder.dao.UserDao;
import com.example.demo.entities.Admin;
//import com.cg.onlineResumeBuilder.domain.Users;
import com.example.demo.exception.AdminIdException;
import com.example.demo.service.AdminService;

@RestController
@Validated
@RequestMapping(value = "/admin")
@CrossOrigin(origins="http://localhost:3000")
public class AdminController {
	
	
	@Autowired
	private AdminService adminService;

	@Autowired
	private AdminRepository adminRepository;
	/*
	 * @Autowired private UserDao userdao;
	 */
	
	@Autowired
	private com.example.demo.service.MapValidationErrorService mapValidationErrorService;
	@PostMapping("/login")
	public ResponseEntity<String> AdminLogin(@Valid @RequestBody Admin admin, BindingResult result) {
		String Login;
		ResponseEntity<?> errorMap =  mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null) return (ResponseEntity<String>) errorMap;
		boolean statusOfLogin = adminService.adminLogin(admin);
		if (statusOfLogin!=false) {  Login="Login Succesfully";return new ResponseEntity<>(Login, HttpStatus.OK);}
		else{  Login="Login Failed";
		return new ResponseEntity<>(Login, HttpStatus.BAD_REQUEST);}
	}
	
	
	@PutMapping("/forgetpassword")
	public ResponseEntity<String> forgetpassword(@Valid @RequestBody Admin admin, BindingResult result) {
		
		ResponseEntity<?> errorMap =  mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null) return (ResponseEntity<String>) errorMap;
		Optional<Admin> admin1=adminRepository.findById(admin.getId());
		if(admin1!=null && admin1.get().getUsername().hashCode()==admin.getUsername().hashCode()) {
		adminService.forgetPassword(admin);return new ResponseEntity<String>("Admin Successfully Updated",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Admin Not Found with given credentials",HttpStatus.BAD_REQUEST);
		}
				
	}
}
	               
/*
 * @GetMapping("/userslist") public Iterable<Users> getAllUsers(){ return
 * userRepository.findAll(); }
 * 
 * 
 * @PostMapping("/adduser") public ResponseEntity<Users>
 * addUsers(@Valid @RequestBody Users user, BindingResult result) {
 * 
 * ResponseEntity<?> errorMap =
 * mapValidationErrorService.mapValidationError(result); if(errorMap!=null)
 * return (ResponseEntity<Users>) errorMap; Users adduser = userdao.save(user);
 * return new ResponseEntity<>(adduser, HttpStatus.CREATED); }
 * 
 * @DeleteMapping("/delete/{username}") public ResponseEntity<String>
 * deleteUser(@PathVariable String username){ userdao.deleteById(username);
 * return new ResponseEntity<>("Deleted!!!", HttpStatus.OK); }
 */
