<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="<spring:url value="css/app.css" />" rel="stylesheet"
	type="text/css">
<title>Welcome</title>
</head>
<body class="security-app">
<ul>
  <li><a href="/login">Login</a></li>
  <li><a  class="active" href="/home">Home</a></li>
  <li><a href="/webcam">Webcam</a></li>
  <li><a href="/webcam/about">About</a></li>
</ul>
 
	<div class="details">
	 
	</div>
	<div class="lc-block">
		<h1>Welcome!</h1>
		<div class="alert-normal">
			Click <a href="<spring:url value='/webcam' />">here</a> to see a
			greeting.
		</div>
	</div>
</body>
</html>
