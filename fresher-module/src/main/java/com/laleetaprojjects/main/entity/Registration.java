package com.laleetaprojjects.main.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Entity
public class Registration {

	@Id
	private String username;
	@Pattern(regexp = "[A-Za-z0-9]{5,}", message = " Strong pass atleast of length 5 ONLY ALPHANUMERIC e.g:riya321")
	private String password;
	@Email(message = "Enter valid mail id...")
	private String mail;
	

	public Registration(
			@Pattern(regexp = "[A-Za-z0-9]{5,}", message = " Strong pass atleast of length 5 ONLY ALPHANUMERIC e.g:riya321") String password,
			@Email(message = "Enter valid mail id...") String mail, String username) {

		this.password = password;
		this.mail = mail;
		this.username = username;
	}

	public Registration() {

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
