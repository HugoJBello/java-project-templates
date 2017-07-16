package com.hjbello.restfulws;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestCapture {

	private int seconds;

	public RequestCapture(int seconds) {
		super();
		this.seconds = seconds;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public RequestCapture() {
		super();
 	}
	
}
