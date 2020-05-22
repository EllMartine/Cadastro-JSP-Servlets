<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/estilo.css">
</head>
<body>
	<h1>Cadastro de usuário</h1>
	<div class="container">
	
	<h2>Cadastro</h2>
	
	<form action="ServletUsuario" method="post">
	
			<input type="text" readonly="readonly" id="id" name="id" placeholder="ID" value="${usuario.id}">
		
			<input type="text" id="login" name="login" placeholder="LOGIN" value="${usuario.login}">
			
			<input type="password" id="senha" name="senha" placeholder="SENHA" value="${usuario.senha}">
			
			<input type="text" id="nome" name="nome" placeholder="NOME" value="${usuario.nome}">
			
			<button type="submit">Cadastrar</button>
	
	</form>
	</div>
	<table>
			<th>ID</th><th>LOGIN</th><th>NOME</th><th>EXCLUIR</th><th>EDITAR</th>
		<c:forEach items="${usuarios}" var="usuario">
			<tr>
				<td><c:out value="${usuario.id}"></c:out></td>
				<td><c:out value="${usuario.login}"></c:out></td>
				<td><c:out value="${usuario.nome}"></c:out></td>
				<td><a href="ServletUsuario?acao=delete&usuario=${usuario.login}"><img src="resources/imagens/excluir.jpg" width="20px"></a></td>
				<td><a href="ServletUsuario?acao=editar&usuario=${usuario.login}"><img src="resources/imagens/editar.png" width="20px"></a></td>
			</tr>
		</c:forEach>
	</table>	
</body>
</html>