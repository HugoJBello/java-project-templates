package com.hjbello.dao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "cathegories")
public class Cathegory implements Serializable {

	public Cathegory(String cathegoryName, String title, String description, Date updatedAt, Date createdAt,
			Integer updatedBy, Integer createdBy) {
		super();
		this.cathegoryName = cathegoryName;
		this.title = title;
		this.description = description;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
		this.updatedBy = updatedBy;
		this.createdBy = createdBy;
	}


	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "cathegory_name")
    private String cathegoryName;   
	
	@Column(name = "title")
    private String title;
	
	@Column(name = "description")
    private String description;
	
	@Column(name = "updated_at")
    private Date updatedAt;
	
	@Column(name = "created_at")
    private Date createdAt;
	
	@Column(name = "updated_by")
    private Integer updatedBy;
	
	@Column(name = "created_by")
    private Integer createdBy;

	
	public Cathegory() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getCathegoryName() {
		return cathegoryName;
	}


	public void setCathegoryName(String cathegoryName) {
		this.cathegoryName = cathegoryName;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Integer getUpdatedBy() {
		return updatedBy;
	}


	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}


	public Integer getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
  
}