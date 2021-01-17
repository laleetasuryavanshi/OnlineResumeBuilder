package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class AcademicInfo {
	@Id
	@GeneratedValue
	private Long id;
	@NotEmpty(message = "Provide HSC percentage")
	private String hscPercent;
	@NotEmpty(message = "Provide SSC percentage")
	private String sscPercent;
	private String degree;
	private String branch;
	private String degreePercent;
	private int noOfBacklogs;
	private int yeargap;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fid", referencedColumnName = "fresherId")
	private FresherInfo fresherinfo;

	public AcademicInfo(@NotEmpty(message = "Provide HSC percentage") String hscPercent,
			@NotEmpty(message = "Provide SSC percentage") String sscPercent, String degree, String branch,
			String degreePercent, int noOfBacklogs, int yeargap) {

		this.hscPercent = hscPercent;
		this.sscPercent = sscPercent;
		this.degree = degree;
		this.branch = branch;
		this.degreePercent = degreePercent;
		this.noOfBacklogs = noOfBacklogs;
		this.yeargap = yeargap;
	}

	public AcademicInfo() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHscPercent() {
		return hscPercent;
	}

	public void setHscPercent(String hscPercent) {
		this.hscPercent = hscPercent;
	}

	public String getSscPercent() {
		return sscPercent;
	}

	public void setSscPercent(String sscPercent) {
		this.sscPercent = sscPercent;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getDegreePercent() {
		return degreePercent;
	}

	public void setDegreePercent(String degreePercent) {
		this.degreePercent = degreePercent;
	}

	public int getNoOfBacklogs() {
		return noOfBacklogs;
	}

	public void setNoOfBacklogs(int noOfBacklogs) {
		this.noOfBacklogs = noOfBacklogs;
	}

	public int getYeargap() {
		return yeargap;
	}

	public void setYeargap(int yeargap) {
		this.yeargap = yeargap;
	}

	public long getFresherinfo() {
		return fresherinfo.getFresherId();
	}

	public void setFresherinfo(FresherInfo fresherinfo) {
		this.fresherinfo = fresherinfo;
	}

}
