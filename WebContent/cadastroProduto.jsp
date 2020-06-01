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
	
	<nav class="menu">
	<ul>
		<li><a href="index.jsp"><img src="resources/imagens/porta.svg" width="20px"></a></li>
		<li><a href="acessoLiberado.jsp"><img src="resources/imagens/home.png" width="20px"></a></li>
	</ul>
	</nav>
	
	<center><h3>${mensagem}</h3></center>
	
	<div class="container">
		
		<img class="imgForm" src="resources/imagens/produto2.png" width="80px">
		
		<form action="ServletProduto" method="post" id="formProd" onsubmit=" return validarCampos() ? true : false">
			<input class="input" type="text" readonly="readonly" id="id" name="id" placeholder="ID" value="${produto.id}">
			
			<input class="input" type="text" id="nome" name="nome" placeholder="NOME" value="${produto.nome}">
			
			<input class="input" type="text" id="quantidade" name="quantidade" placeholder="QUANTIDADE" value="${produto.quantidade}">
			
			<input class="input" type="text" id="valor" name="valor" placeholder="VALOR" value="${produto.valor}">
			
			<button class="button" type="submit">Cadastrar</button>
			<button class="button" onclick="document.getElementById('formProd').action='ServletProduto?acao=cancelar'">Cancelar</button>
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
	
	<script type="text/javascript">
	
	function validarCampos() {
		
		if (document.getElementById("nome").value == "") {
			alert("Informe o nome");
			return false;
		} else if (document.getElementById("quantidade").value == "") {
			alert("Informe a quantidade");
			return false;
		} else if (document.getElementById("valor").value == "") {
			alert("Informe o valor");
			return false;
		} 
		return true;
	}
	
	</script>
</body>
</html>