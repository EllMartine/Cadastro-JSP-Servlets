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

	<center><h3>${mensagem}</h3></center>
	
	<div class="container">
		
		<h2>Produto</h2>
		
		<form action="ServletProduto" method="post" id="formProd">
			<input class="input" type="text" readonly="readonly" id="id" name="id" placeholder="ID" value="${produto.id}">
			
			<input class="input" type="text" id="nome" name="nome" placeholder="NOME" value="${produto.nome}">
			
			<input class="input" type="text" id="quantidade" name="quantidade" placeholder="QUANTIDADE" value="${produto.quantidade}">
			
			<input class="input" type="text" id="valor" name="valor" placeholder="VALOR" value="${produto.valor}">
			
			<button class="button" type="submit">Cadastrar</button>
			<button class="button" onclick="document.getElementById('formProd').action='ServletUsuario?acao=cancelar'">Cancelar</button>
		</form>
	</div>
	
	<table>
			<th>ID</th><th>NOME</th><th>QUANTIDADE</th><th>VALOR</th><th>EXCLUIR</th><th>EDITAR</th>
		<c:forEach items="${produtos}" var="produto">
			<tr>
				<td><c:out value="${produto.id}"></c:out></td>
				<td><c:out value="${produto.nome}"></c:out></td>
				<td><c:out value="${produto.quantidade}"></c:out></td>
				<td><c:out value="${produto.valor}"></c:out></td>
				<td><a href="ServletProduto?acao=delete&produto=${produto.id}"><img src="resources/imagens/excluir.jpg" width="20px"></a></td>
				<td><a href="ServletProduto?acao=editar&produto=${produto.id}"><img src="resources/imagens/editar.png" width="20px"></a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>