<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>JSP</title>
</head>
<body>
	<center>
		<!-- Tag JSTL -->
		<c:out value="Validando login()"></c:out>
		<%-- out.print("Testando o JSP"); --%>
		
		<form action="ServletUsuario" method="post">
			<label for="1">Login:</label>
			<input type="text" id="1" name="login">
			<br>
			<label for="2">Senha:</label>
			<input type="text" id="2" name="senha">
			<br>
			<input type="submit" value="Submit">
		</form>
	<center>			
</body>
</html>