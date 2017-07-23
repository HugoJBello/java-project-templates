<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" />"></script>
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
		
		<h3>Detect movement</h3>
        <form class="invokeCam" id="invokeCam">
        <table>
				<tr>
					<td>Stop in:</td>
					<td><input type=text id="seconds"></td>
				</tr>
				<tr>
					<td></td>
					<td><button type="submit">capture</button></td>
				</tr>
			</table>
		</form>
		
       <div id="img_preview" style="width:250px; height:250px;"></div>	
       
       	<input type="hidden" id="csrfToken" value="${_csrf.token}"/>
        <input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
	</div>

<script type="text/javascript">
jQuery(document).ready(function($) {
	$("#invokeCam").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		invokeCamAjax();

	});
});

function invokeCamAjax() {
	
	var token = $('#csrfToken').val();
	var header = $('#csrfHeader').val();	
	var data = {}
	data["seconds"] = parseInt($("#seconds").val());

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "${home}/invokeRestful/",
		data : JSON.stringify(data),
		dataType : 'json',
		beforeSend: function(xhr) {
	        xhr.setRequestHeader("Accept", "application/json");
	        xhr.setRequestHeader("Content-Type", "application/json");
	        xhr.setRequestHeader(header, token);
	    },
		timeout : 9900000,
		success : function(data) {
			console.log("SUCCESS: ", data);
			//display(data);
			display(data);
		},
		error : function(e) {
			console.log("ERROR: ", e);
			display(e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
}

function display (data){
 	var base64_string = data.imagesBase64[0];
    var $img = $("<img/>");
    $img.attr("src", "data:image/png;base64," + base64_string);
    $("#img_preview").append($img);	
}
</script>

</body>
</html>
