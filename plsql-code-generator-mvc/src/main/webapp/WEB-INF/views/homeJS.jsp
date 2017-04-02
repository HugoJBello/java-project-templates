<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<title>Home</title>
<link href="<spring:url value="/resources/static/css/app.css" />" rel="stylesheet" type="text/css">
</head>
<body>
	<h1>dbms_output generator</h1>
	 
	
	<form:form method="post" modelAttribute="codeForm">
		<table>
			<tr>
				<td></td>
				<td>Paste your code here</td>
				<td>Generated code</td>
			</tr>
			<tr>
				<td><div id="commands">
						Obtain dbms_output from:<br>
						<form:radiobutton id="codeOriginObject" path="codeOrigin" value="from object" />
						from an object<br>
						<form:radiobutton id="codeOriginType" path="codeOrigin" value="from type" />
						from a type<br>
						<form:checkbox id="withLoop" path="withLoop" checked="checked"/>
						with loop <br>
						<table>
							<tr>
								<td>Index name
								</td>
								<td><form:input id="indexName" path="indexName" />
								</td>
							</tr>
							<tr>
								<td>Variable name </td>
								<td> <form:input id="variableName"
										path="variableName" /></td>
							</tr>
						</table>
					</div></td>
				<td><form:textarea id = "inputText" path="inputText" cols="45" rows="10"></form:textarea></td>
				<td><form:textarea id = "outputText" path="outputText" cols="45" rows="10"></form:textarea></td>
			</tr>
			<tr>
				<td></td>
				<td><input id="generateButton" type="button" value="Convert" onclick="generateDbmsOuput()"/></td>
				<td></td>
			</tr>
		</table>
	</form:form>
	
<script type="text/javascript" src="<spring:url value="/resources/static/js/home.js" />"></script>
</body>
</html>
