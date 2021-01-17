package com.example.demo.service;

import com.example.demo.entities.Admin;

public interface IAdminService {
	
	boolean adminLogin(Admin admin);
	

  void forgetPassword(Admin admin);
    
	}
