package com.hjbello.main;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@Component(value="codeForm")
@XmlRootElement
public class CodeForm {

	private String inputText ="..";
	private String outputText ="";
	private String codeOrigin = "from object";
	private String withLoop ="N";
	private String indexName = "i";
	private String variableName = "result";
	private String error = "";

	public CodeForm (){

	}

	public CodeForm(String inputText, String outputText, String codeOrigin, String withLoop, String indexName,
			String variableName) {
		super();
		this.inputText = inputText;
		this.outputText = outputText;
		this.codeOrigin = codeOrigin;
		this.withLoop = withLoop;
		this.indexName = indexName;
		this.variableName = variableName;
	}
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getWithLoop() {
		return withLoop;
	}

	public void setWithLoop(String withLoop) {
		this.withLoop = withLoop;
	}

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public String getCodeOrigin() {
		return codeOrigin;
	}

	public void setCodeOrigin(String codeOrigin) {
		this.codeOrigin = codeOrigin;
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
