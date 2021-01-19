package com.laleetaprojjects.main.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Hobbies {
	@Id
	@GeneratedValue
	private long id;
	private String hobbyName;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fid", referencedColumnName = "fresherId")
	private FresherInfo fresherinfo;

	public Hobbies(String hobbyName) {

		this.hobbyName = hobbyName;
	}

	public Hobbies() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHobbyName() {
		return hobbyName;
	}

	public void setHobbyName(String hobbyName) {
		this.hobbyName = hobbyName;
	}

	public long getFresherinfo() {
		return fresherinfo.getFresherId();
	}

	public void setFresherinfo(FresherInfo fresherinfo) {
		this.fresherinfo = fresherinfo;
	}

}
