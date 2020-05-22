<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>JSP</title>
<link rel="stylesheet" href="resources/css/estilo.css">
</head>
<body>

		
	<div class="container">
	
		<!-- Tag JSTL -->
		<!--<c:out value="Validando login()"></c:out>-->
		<%-- out.print("Testando o JSP"); --%>
		<h2>Portal Login</h2>
	
		<form action="ServletLogin" method="post">
			<!--<label for="1">Login:</label>-->
			<input type="text" id="1" name="login" placeholder="Login">
			<br>
			<!--<label for="2">Senha:</label>-->
			<input type="password" id="2" name="senha" placeholder="Senha">
			<br>
			<button type="submit">Submit</button>
		</form>
	</div>
				
</body>
</html>