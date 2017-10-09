package com.hjbello.controllers.utils;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@Component(value="newEntryForm")
@XmlRootElement

public class NewEntryForm {
	
	private String entryName;
	private String title;
	private String contents;
	private String cathegories;
	
	public String getEntryName() {
		return entryName;
	}
	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public NewEntryForm(String entryName, String title, String contents) {
		super();
		this.entryName = entryName;
		this.title = title;
		this.contents = contents;
	}
	public NewEntryForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCathegories() {
		return cathegories;
	}
	public void setCathegories(String cathegories) {
		this.cathegories = cathegories;
	}

}
