package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AdminRepository;
import com.example.demo.entities.Admin;

@Component
@Service
public class AdminService implements IAdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public boolean adminLogin(Admin admin) {
	Optional<Admin> admin1=adminRepository.findById(admin.getId());
	
	if(admin1!=null && admin1.hashCode()==admin.hashCode() ) {
		return true;
	}
	else {
		return false;
	}
	}

	@Override
	public  void forgetPassword(Admin admin) {
		
		   admin.setPassword(admin.getPassword());
		  adminRepository.save(admin);
		 
		  
	}

}
