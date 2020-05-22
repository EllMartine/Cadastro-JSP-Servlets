<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Bem vindo ao cadastro de usuário</h1>
	<form action="ServletUsuario" method="post">
		<table>
	   <!-- <tr><td><label for="id">ID:</label></td></tr>
			<tr><td><input type="text" readonly="readonly" id="id" name="id" value="${usuario.id}"></td></tr> -->
		
		<tr><td><label for="login">Login:</label></td></tr>
			<tr><td><input type="text" id="login" name="login" value="${usuario.login}"></td></tr>
			
			<tr><td><label for="senha">Senha:</label></td></tr>
			<tr><td><input type="password" id="senha" name="senha" value="${usuario.senha}"></td></tr>
			
			<tr><td><input type="submit" value="Cadastrar"></td></tr>
		</table>
	</form>
	
	<table>
		<c:forEach items="${usuarios}" var="usuario">
			<tr>
				<td><c:out value="${usuario.id}"></c:out></td>
				<td><c:out value="${usuario.login}"></c:out></td>
				<td><c:out value="${usuario.senha}"></c:out></td>
				<td><a href="ServletUsuario?acao=delete&usuario=${usuario.login}">Excluir</a></td>
				<td><a href="ServletUsuario?acao=editar&usuario=${usuario.login}">Editar</a></td>
			</tr>
		</c:forEach>
	</table>	
</body>
</html>