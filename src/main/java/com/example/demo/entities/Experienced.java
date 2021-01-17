package com.example.demo.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//import dao.Project;
//import dao.Skill;

@Entity
@ApiModel(description = "This is Experienced model which contains basic personal details.")
public class Experienced {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes = "This Experienced ID which represents primary key column in database table")
	private int id;
	
	@NotEmpty(message = "Please provide first name.")
	private String firstName;
	@NotEmpty(message = "Please provide last name.")
	private String lastName;
	@Pattern( regexp = "^[6789]{1}[0-9]{9}$", message = "Please enter valid phone number.")
	private String phone;
	@NotEmpty(message = "Please provide city.")
	private String city;
	@NotEmpty(message = "Please provide state.")
	private String state;
	@NumberFormat(style = Style.NUMBER, pattern = "######")
	private int pincode;
	@Pattern( regexp = "^([A-Za-z0-9+_.-]+)@([a-zA-Z0-9-]+).([a-z]{2,8})(.[a-z]{2,8})?$", message = "Please enter valid email address.") 
	private String email;
	
	@OneToOne(mappedBy = "experienced", cascade = CascadeType.ALL)
	private WorkEx workex;

	@OneToOne(mappedBy = "experienced", cascade = CascadeType.ALL)
	private Education education;

	@OneToMany(mappedBy = "experienced", cascade = CascadeType.ALL)
	private Set<Project> projects;

	@OneToMany(mappedBy = "experienced", cascade = CascadeType.ALL)
	private Set<Skill> skills;

	public Experienced(@NotEmpty(message = "Please provide first name") String firstName, 
			@NotEmpty(message = "Please provide last name") String lastName, @Pattern( regexp = "^[6789]{1}[0-9]{9}$", message = "Please enter valid phone number.") String phone, 
			@NotEmpty(message = "Please provide city") String city,@NotEmpty(message = "Please provide state") String state,@NumberFormat(style = Style.NUMBER, pattern = "######") int pincode,
			@Pattern( regexp = "^([A-Za-z0-9+_.-]+)@([a-zA-Z0-9-]+).([a-z]{2,8})(.[a-z]{2,8})?$", message = "Please enter valid email address.") String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.email = email;
		this.projects = new HashSet<>();
		this.skills = new HashSet<>();
	}

	public Experienced() {
		this.projects = new HashSet<>();
		this.skills = new HashSet<>();
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	public WorkEx getWorkex() {
		return workex;
	}

	public void setWorkex(WorkEx workex) {
		this.workex = workex;
	}

	public Education getEducation() {
		return education;
	}

	public void setEducation(Education education) {
		this.education = education;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}

}
