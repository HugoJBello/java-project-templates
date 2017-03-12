<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Pl/SQL code generator</title>
</head>
<style>
textarea {
	background-color: #d1d1d1;
}

input[type=submit] {
	background-color: #FB031C;
}

body, h1 {
	margin-top: 30px;
	margin-bottom: 100px;
	margin-right: 80px;
	margin-left: 80px;
}
/* div {
background-color: #FB031C} */
</style>
<body>
	<h2>dbms_output generator</h2>


	<form:form method="post" action="send" modelAttribute="codeForm">
		<table>
			<tr>
				<td></td>
				<td>Paste your code here</td>
				<td>Generated code</td>
			</tr>
			<tr>
				<td><div id="commands">
						Obtain dbms_output from:<br>
						<form:radiobutton path="codeOrigin" value="from object" />
						from an object<br>
						<form:radiobutton path="codeOrigin" value="from type" />
						from a type<br>
						<form:checkbox path="withLoop" value="N" />
						with loop <br>
						<table>
							<tr>
								<td>Index name
								</td>
								<td><form:input path="indexName" />
								</td>
							</tr>
							<tr>
								<td>Variable name </td>
								<td> <form:input
										path="variableName" /></td>
							</tr>
						</table>
					</div></td>
				<td><form:textarea path="inputText" cols="45" rows="10"></form:textarea></td>
				<td><form:textarea path="outputText" cols="45" rows="10"></form:textarea></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Generate" /></td>
				<td></td>
			</tr>
		</table>
	</form:form>

<%-- 	<P>The time on the server is ${serverTime}.</P>
 --%>
 </body>
</html>
