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
@ApiModel(description = "This is Skill model which contains details of skills.")
public class Skill {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes = "This Skill ID which represents primary key column in database table")
	private int sid;
	private String skills;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exid", referencedColumnName = "id")
	private Experienced experienced;

	public Skill() {

	}

	public Skill(String skills) {
		this.skills = skills;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public int getExperienced() {
		return this.experienced.getId();
	}

	public void setExperienced(Experienced experienced) {
		this.experienced = experienced;
	}

}
