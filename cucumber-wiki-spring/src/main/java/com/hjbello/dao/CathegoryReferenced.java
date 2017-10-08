package com.hjbello.dao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "cathegories_referenced")
public class CathegoryReferenced implements Serializable {



	private static final long serialVersionUID = 1L;
	
	@Column(name = "cathegory_name")
    private String cathegoryName;
	
	@Column(name = "cathegory_title")
    private String cathegoryTitle;  
	
	@Column(name = "entry_name_referenced")
    private String entryNameReferenced;
	
	
	@Column(name = "updated_at")
    private Date updatedAt;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "reference_id")
    private Long referenceId;
	
	public CathegoryReferenced() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCathegoryName() {
		return cathegoryName;
	}

	public void setCathegoryName(String cathegoryName) {
		this.cathegoryName = cathegoryName;
	}


	public Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}


	public String getEntryNameReferenced() {
		return entryNameReferenced;
	}


	public void setEntryNameReferenced(String entryNameReferenced) {
		this.entryNameReferenced = entryNameReferenced;
	}


	public String getCathegoryTitle() {
		return cathegoryTitle;
	}


	public void setCathegoryTitle(String cathegoryTitle) {
		this.cathegoryTitle = cathegoryTitle;
	}

	public Long getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(Long referenceId) {
		this.referenceId = referenceId;
	}


}