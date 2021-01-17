package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Projects {
	@Id
	@GeneratedValue
	private int id;
	private String projectName;
	private int year;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fid", referencedColumnName = "fresherId")
	private FresherInfo fresherinfo;

	public Projects(String projectName, int year) {

		this.projectName = projectName;
		this.year = year;
	}

	public Projects() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public long getFresherinfo() {
		return fresherinfo.getFresherId();
	}

	public void setFresherinfo(FresherInfo fresherinfo) {
		this.fresherinfo = fresherinfo;
	}

}
