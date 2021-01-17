package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(description = "This is Fresher Model Which Represents Database Table")
public class FresherInfo {
	@ApiModelProperty(notes = "This Fresher ID Represents Primary Key Column in Database Table", example = "12", required = true)
	@Id
	@GeneratedValue
	private long fresherId;
	@NotEmpty(message = "Provide valid name...Blank not accepted")
	private String fresherName;
	@Email(message = "Provide valid mail id....")
	private String email;
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Enter valid 10 digit mobile no.")

	private String contactNo;

	// (message = "Provide age...Empty Not accepted..")
	private int age;
	private String address;
	@OneToMany(mappedBy = "fresherinfo", cascade = CascadeType.ALL)
	// fresherinfo in skill class FresherInfo obj...

	private List<SkillInfo> skills;
	@OneToOne(mappedBy = "fresherinfo", cascade = CascadeType.ALL)
	private AcademicInfo academic;
	@OneToMany(mappedBy = "fresherinfo", cascade = CascadeType.ALL)
	private List<Hobbies> hobbies;
	@OneToMany(mappedBy = "fresherinfo", cascade = CascadeType.ALL)
	private List<Projects> projects;

	public FresherInfo() {
		this.skills = new ArrayList<>();
		this.hobbies = new ArrayList<>();
		this.projects = new ArrayList<>();
	}

	public FresherInfo(long fresherId,
			@NotEmpty(message = "Provide valid name...Blank not accepted") String fresherName,
			@Email(message = "Provide valid mail id....") String email,
			@Pattern(regexp = "(^$|[0-9]{10})", message = "Enter valid 10 digit mobile no.") String contactNo, int age,
			String address) {

		this.fresherId = fresherId;
		this.fresherName = fresherName;
		this.email = email;
		this.contactNo = contactNo;
		this.age = age;
		this.address = address;
	}

	public FresherInfo(
			@Pattern(regexp = "^[A-Za-z ]+$", message = "Provide valid name...Blank not accepted") String fresherName,
			@Email(message = "Provide valid mail id....") String email,
			@Pattern(regexp = "(^$|[0-9]{10})", message = "Enter valid 10 digit mobile no.") String contactNo, int age,
			String address) {

		this.fresherName = fresherName;
		this.email = email;
		this.contactNo = contactNo;
		this.age = age;
		this.address = address;
	}

	public List<SkillInfo> getSkills() {
		return skills;
	}

	public void setSkills(List<SkillInfo> skills) {
		this.skills = skills;
	}

	public long getFresherId() {
		return fresherId;
	}

	public void setFresherId(long fresherId) {
		this.fresherId = fresherId;
	}

	public String getFresherName() {
		return fresherName;
	}

	public void setFresherName(String fresherName) {
		this.fresherName = fresherName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public AcademicInfo getAcademic() {
		return academic;
	}

	public void setAcademic(AcademicInfo academic) {
		this.academic = academic;
	}

	public List<Hobbies> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<Hobbies> hobbies) {
		this.hobbies = hobbies;
	}

	public List<Projects> getProjects() {
		return projects;
	}

	public void setProjects(List<Projects> projects) {
		this.projects = projects;
	}

}
