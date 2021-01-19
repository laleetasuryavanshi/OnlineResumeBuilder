package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(description = "This is Project model which contains details of projects completed.")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes = "This Project ID which represents primary key column in database table")
	private int pid;
	private String projects;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exid", referencedColumnName = "id")
	private Experienced experienced;

	public Project() {

	}

	public Project(String projects) {
		this.projects = projects;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getProjects() {
		return projects;
	}

	public void setProjects(String projects) {
		this.projects = projects;
	}

	public int getExperienced() {
		return this.experienced.getId();
	}

	public void setExperienced(Experienced experienced) {
		this.experienced = experienced;
	}

}
