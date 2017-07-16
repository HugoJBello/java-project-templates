package com.hjbello.config;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@Component(value="detectFom")
@XmlRootElement
public class DetectForm {
	private String seconds = "";
	private String folder = System.getProperty("user.home");
	public DetectForm(){
		
	}
	public DetectForm(String seconds, String folder) {
		super();
		this.seconds = seconds;
		this.folder = folder;
	}
	
	public String getSeconds() {
		return seconds;
	}
	public void setSeconds(String seconds) {
		this.seconds = seconds;
	}
	public String getFolder() {
		return folder;
	}
	public void setFolder(String folder) {
		this.folder = folder;
	}

}
