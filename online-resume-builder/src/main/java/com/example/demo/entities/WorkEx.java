package com.example.demo.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



@Entity
@ApiModel(description = "This is WorkEx model which contains past work experience details.")
public class WorkEx {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes = "This WorkEx ID which represents primary key column in database table")
	private int wid;
	@NotEmpty(message = "Please provide your previous job title.")
	private String jobTitle;
	@NotEmpty(message = "Please provide the name of your previous employer.")
	private String employerName;
	@NotEmpty(message = "Please provide city.")
	private String wcity;
	@NotEmpty(message = "Please provide state.")
	private String wstate;
	private Date fromDate;
	private Date toDate;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exid", referencedColumnName = "id")
	private Experienced experienced;

	public WorkEx() {

	}

	public WorkEx(@NotEmpty(message = "Please provide your previous job title.") String jobTitle, 
			@NotEmpty(message = "Please provide the name of your previous employer.") String employerName, 
			@NotEmpty(message = "Please provide city.") String wcity, 
			@NotEmpty(message = "Please provide state.") String wstate, Date fromDate, Date toDate) {
		super();
		this.jobTitle = jobTitle;
		this.employerName = employerName;
		this.wcity = wcity;
		this.wstate = wstate;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public int getWid() {
		return wid;
	}

	public void setWid(int wid) {
		this.wid = wid;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getEmployerName() {
		return employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public String getWcity() {
		return wcity;
	}

	public void setWcity(String wcity) {
		this.wcity = wcity;
	}

	public String getWstate() {
		return wstate;
	}

	public void setWstate(String wstate) {
		this.wstate = wstate;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public int getExperienced() {
		return this.experienced.getId();
	}

	public void setExperienced(Experienced experienced) {
		this.experienced = experienced;
	}

}
