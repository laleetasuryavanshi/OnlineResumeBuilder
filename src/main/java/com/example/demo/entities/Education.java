package com.example.demo.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Entity
@ApiModel(description = "This is Education model which contains qualification details.")
public class Education {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes = "This Education ID which represents primary key column in database table")
	private int eid;
	@Min(1)
	@Max(100)
	private double ssc;
	@Min(1)
	@Max(100)
	private double hsc;
	@NotEmpty(message = "Please provide name of your degree.")
	private String degreeName;
	@Min(1)
	@Max(100)
	private double degreePercent;
	@NotEmpty(message = "Please provide your field of study.")
	private String fieldOfStudy;
	private Date startDate;
	private Date endDate;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exid", referencedColumnName = "id")
	private Experienced experienced;

	public Education(@Min(1) @Max(100) double ssc,@Min(1) @Max(100) double hsc, 
			@NotEmpty(message = "Please provide name of your degree.") String degreeName,@Min(1) @Max(100) double degreePercent, 
			@NotEmpty(message = "Please provide your field of study.") String fieldOfStudy,
			Date startDate, Date endDate) {
		this.ssc = ssc;
		this.hsc = hsc;
		this.degreeName = degreeName;
		this.degreePercent = degreePercent;
		this.fieldOfStudy = fieldOfStudy;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Education() {

	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public double getSsc() {
		return ssc;
	}

	public void setSsc(double ssc) {
		this.ssc = ssc;
	}

	public double getHsc() {
		return hsc;
	}

	public void setHsc(double hsc) {
		this.hsc = hsc;
	}

	public String getDegreeName() {
		return degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	public double getDegreePercent() {
		return degreePercent;
	}

	public void setDegreePercent(double degreePercent) {
		this.degreePercent = degreePercent;
	}

	public String getFieldOfStudy() {
		return fieldOfStudy;
	}

	public void setFieldOfStudy(String fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getExperienced() {
		return this.experienced.getId();
	}

	public void setExperienced(Experienced experienced) {
		this.experienced = experienced;
	}

}
