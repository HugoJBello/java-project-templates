<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
 
<body>
	<h1>dbms_output generator</h1>

	<!-- <form method="post" action="send">
		<table>
			<tr>
				<td><textarea name="id"  cols="45" rows="10"></textarea></td>
				<td><textarea name="output" cols="45" rows="10"></textarea></td>
 			</tr>
			<tr>
				<td>Eve</td>
				<td>Jackson</td>
 			</tr>
		</table>
		<input type="text" name="id2" /> <input type="submit" />
	</form> -->


 <form:form method="post" action="send" modelAttribute="Input">
		<table>
			<tr>
				<td><form:textarea path="inputText"  cols="45" rows="10"></form:textarea></td>
				<td><form:textarea path="outputText"  cols="45" rows="10"></form:textarea></td>
			</tr>
			<tr>
				<td>Eve</td>
				<td>Jackson</td>
			</tr>
		</table>
		<input type="submit" value="Generate" />
	</form:form>
 
	<P>The time on the server is ${serverTime}.</P>
</body>
</html>
