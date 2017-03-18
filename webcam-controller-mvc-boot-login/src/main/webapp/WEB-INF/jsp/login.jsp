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
<title>Spring Security Example</title>
</head>
<body class="security-app">
	<ul>
  <li><a class="/active" href="login">Login</a></li>
  <li><a href="/home">Home</a></li>
  <li><a href="/webcam">Webcam</a></li>
  <li><a href="/webcam/about">About</a></li>
</ul>
	<form action="/login" method="post">
		<div class="login">
			<table>
				<tr>
					<td><label>Username</label></td>
					<td><input type="text" name="username"
						placeholder="User Name" /></td>
				</tr>
				<tr>
					<td><label>Password</label></td>
					<td><input type="password" name="password"
						placeholder="Password" /></td>
				</tr>
			</table>
			<input type="submit" value="Sign In" class="button red small" />
			<c:if test="${param.error ne null}">
				<div class="alert-danger">Invalid username and password.</div>
			</c:if>
			<c:if test="${param.logout ne null}">
				<div class="alert-normal">You have been logged out.</div>
			</c:if>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</div>
	</form>
</body>
</html>
