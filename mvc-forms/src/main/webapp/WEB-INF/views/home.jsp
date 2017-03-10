<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
<form action="send">
<input type="text" name="id" />
<input type="submit" />
</form>
<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
