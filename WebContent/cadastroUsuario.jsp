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
	<!--<h1>Cadastro de usuário</h1>-->
	
	<center><h3>${mensagem}</h3></center>
	
	<div class="container">
	
	<img class="imgForm" src="resources/imagens/usuario.png" width="80px">
	
	<form action="ServletUsuario" method="post" id="formUser">
	
			<input class="input" type="text" readonly="readonly" id="id" name="id" placeholder="ID" value="${usuario.id}">
		
			<input class="input" type="text" id="login" name="login" placeholder="LOGIN" value="${usuario.login}">
			
			<input class="input" type="password" id="senha" name="senha" placeholder="SENHA" value="${usuario.senha}">
			
			<input class="input" type="text" id="nome" name="nome" placeholder="NOME" value="${usuario.nome}">
			
			<input class="input" type="text" id="telefone" name="telefone" placeholder="TELEFONE" value="${usuario.telefone}">
			
			<button class="button" type="submit">Cadastrar</button>
			<button class="button" onclick="document.getElementById('formUser').action='ServletUsuario?acao=cancelar'">Cancelar</button>
	
	</form>
	</div>
	
	<table>
			<th>ID</th><th>LOGIN</th><th>NOME</th><th>TELEFONE</th><th>EXCLUIR</th><th>EDITAR</th>
		<c:forEach items="${usuarios}" var="usuario">
			<tr>
				<td><c:out value="${usuario.id}"></c:out></td>
				<td><c:out value="${usuario.login}"></c:out></td>
				<td><c:out value="${usuario.nome}"></c:out></td>
				<td><c:out value="${usuario.telefone}"></c:out></td>
				<td><a href="ServletUsuario?acao=delete&usuario=${usuario.id}"><img src="resources/imagens/excluir.jpg" width="20px"></a></td>
				<td><a href="ServletUsuario?acao=editar&usuario=${usuario.id}"><img src="resources/imagens/editar.png" width="20px"></a></td>
			</tr>
		</c:forEach>
	</table>	
</body>
</html>