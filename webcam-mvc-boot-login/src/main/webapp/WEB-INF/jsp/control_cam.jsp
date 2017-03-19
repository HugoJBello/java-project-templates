<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="css/app.css" />" rel="stylesheet"
	type="text/css">
<title>Hello World!</title>
</head>
<body class="security-app">
	<ul>
		<li><a href="/login">Login</a></li>
		<li><a href="/home">Home</a></li>
		<li><a class="active" href="/webcam">Webcam</a></li>
		<li><a href="webcam/about">About</a></li>
	</ul>
	<div class="lc-block">

		<h3>You are controlling:</h3>
		<table>
			<tr>
				<td>System:</td>
				<td>${operativeSystem}</td>
			</tr>
			<tr>
				<td>User:</td>
				<td>${user}</td>
			</tr>
			<tr>
				<td>Webcam:</td>
				<td>${webcamInfo}</td>
			</tr>
		</table>

		<form:form method="post" action="cam-movement"
			modelAttribute="detectForm">
			<h3>Detect movement</h3>
			<table>
				<tr>
					<td>Stop in:</td>
					<td><form:input type="text" path="seconds" /></td>
				</tr>
				<tr>
					<td>Folder:</td>
					<td><form:input type="text" path="folder" /></td>
				</tr>
			</table>
			<p>
				<input type="submit" value="Start" />
			</p>
			<img alt="bla bla" src="/images/${todaysFolder}/20170318-03_20_36.jpg">
		</form:form>




	</div>

</body>
</html>
