package com.hjbello.dao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "page_entries")
public class PageEntry implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "entry_name")
    private String entryName;   
	
	@Column(name = "title")
    private String Title;
	
	@Column(name = "contents")
    private String contents;
	
	@Column(name = "cathegories")
    private String cathegories;

	@Column(name = "updated_at")
    private Date updatedAt;
	
	@Column(name = "created_at")
    private Date createdAt;
	
	@Column(name = "updated_by")
    private Integer updatedBy;
	
	@Column(name = "created_by")
    private Integer createdBy;

	public String getEntryName() {
		return entryName;
	}

	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getCathegories() {
		return cathegories;
	}

	public void setCathegories(String cathegories) {
		this.cathegories = cathegories;
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

	public PageEntry(String entryName, String title, String contents, String cathegories, Date updatedAt,
			Date createdAt, Integer updatedBy, Integer createdBy) {
		super();
		this.entryName = entryName;
		Title = title;
		this.contents = contents;
		this.cathegories = cathegories;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
		this.updatedBy = updatedBy;
		this.createdBy = createdBy;
	}

	public PageEntry() {
		super();
		// TODO Auto-generated constructor stub
	}

	
  
}