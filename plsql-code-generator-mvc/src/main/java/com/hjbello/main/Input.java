package com.hjbello.main;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@Component(value="Input")
@XmlRootElement
public class Input {


	private String inputText ="..";
	private String outputText ="";
	public Input (){

	}
	public Input(String inputText, String outputText) {
		this.inputText = inputText;
		this.outputText = outputText;
	}
	public String getOutputText() {
		return outputText;
	}

	public void setOutputText(String outputText) {
		this.outputText = outputText;
	}

	public String getInputText() {
		return inputText;
	}

	public void setInputText(String inputText) {
		this.inputText = inputText;
	}
}
